# SubmitFormToTally

A scheduled Selenium suite that exercises a live [Tally](https://tally.so) intake form, which is
the trigger for a self-hosted n8n recruitment workflow. The suite runs unattended four times a
week on GitHub Actions.

## Why this exists

The n8n workflow behind the form is a running system: it validates submissions, logs candidates
to Google Sheets, notifies HR, applies a scoring matrix to shortlist, sends branch-appropriate
emails, and writes an outcome status back to the source record. Nobody watches it day to day.

This suite is the monitoring. It submits traffic through the front door on a schedule and fails
loudly when the form stops accepting submissions, so a broken integration surfaces within days
rather than whenever someone next happens to look. Failures in the workflow itself are caught
separately by a dedicated n8n error workflow that emails the failing node, execution id and
stack trace.

## What it covers

| Suite | Scenario | Expected downstream branch |
|---|---|---|
| `tallyform-no-cv-link.xml` | Submission with no CV link | Re-apply email, no shortlist decision |
| `tallyform-selected.xml` | Warehouse Supervisor, 8+ years | Shortlisted, interview invitation, status `Shortlisted` |
| `tallyform-not-selected.xml` | Logistics Coordinator, 0-2 years | Kept on file, status `Kept on File` |
| `tallyform-7-random-tests.xml` | 7 randomised submissions | Mixed, exercises the full matrix |
| `tallyform-execute-all-tests.xml` | All of the above | All branches |

Each test asserts the Tally confirmation text matches the expected value, so a submission that
is silently rejected fails the run rather than passing on the absence of an exception.

Test data is generated per run with JavaFaker on a Nigerian locale, so no two runs submit
identical candidates.

## Schedule

| Workflow | Cron (UTC) | Suites |
|---|---|---|
| Thrice Weekly | `0 8 * * 1,3,5` | no-CV, selected, not-selected |
| Weekly | `0 8 * * 6` | 7 randomised submissions |

Both support `workflow_dispatch` for manual runs. Surefire reports upload as build artifacts
and are retained for 30 days.

## Design

```
src/main/java/pageobjects/    BasePage, FormPage, CompletionPage  (Page Object Model)
src/test/java/testcases/      BaseTest (WebDriver lifecycle), SubmitFormToTallyTests
src/test/java/utils/          CandidateData, Constants, CvLinks
test-runner/                  TestNG suite definitions, selected by group
```

`FormPage` exposes a fluent interface; `CompletionPage` covers the confirmation screen. The
confirmation locator matches the heading by structure rather than by its text, so the assertion
in the test is what verifies the wording. A text-based locator would be tautological: it could
only ever fail on a missing element.

Chrome runs headless via WebDriverManager, so no driver binaries are committed. Tally is a
React form that ignores plain `sendKeys` in places, so `FormPage` dispatches synthetic `input`
events and uses JavaScript clicks on the radio fieldsets where the native interaction does not
register the state change.

The Chrome options disable `AutomationControlled` and the automation switches, to keep Tally's
client-side handling consistent between local and CI runs. **The target is the author's own
form**; nothing here is pointed at third-party infrastructure.

## Running locally

```bash
cp .env.example .env          # then fill in the real values
export $(grep -v '^#' .env | xargs)

mvn test -DsuiteXmlFile=test-runner/tallyform-execute-all-tests.xml
```

Both values can also be passed as system properties, `-Dtally.form.url=<url>` and
`-Dcv.link.valid=<url>`. The build fails fast with an actionable message if neither source
supplies them.

## Configuration

Nothing environment-specific is committed. Set these as GitHub Actions repository secrets
(Settings, then Secrets and variables, then Actions):

| Secret | Purpose |
|---|---|
| `TALLY_FORM_URL` | The live form the suite submits to |
| `CV_LINK_VALID` | A shareable CV link used by the fixed shortlist scenarios |

## Roadmap

The suite asserts that Tally accepted each submission. It does not yet verify what n8n did with
it, which means a silent failure in the downstream routing would not be caught. Next:

- [ ] Verify the Google Sheets row is created with the submitted values
- [ ] Assert the status write-back matches the expected branch (`Shortlisted` / `Kept on File`)
- [ ] Assert the re-apply path fires for the no-CV case
- [ ] Replace `System.out` with Extent Reports for readable CI output
