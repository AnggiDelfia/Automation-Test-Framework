# Automation Test Framework

Framework automation test Java untuk Web UI dan API dalam satu repository. Test scenario ditulis dengan format Gherkin dan dijalankan memakai Cucumber.

## Tech Stack

- Java 17
- Gradle
- Cucumber JVM
- JUnit Platform
- Selenium WebDriver
- WebDriverManager
- REST Assured
- AssertJ
- GitHub Actions

## Target Test

- Web UI: [Demoblaze](https://www.demoblaze.com/)
- API: [DummyAPI](https://dummyapi.io/docs)

## Project Structure

```text
src/test/java/com/example/framework
├── api
│   ├── clients
│   └── steps
├── web
│   ├── pages
│   └── steps
└── RunCucumberTest.java

src/test/resources/features
├── api
└── web
```

## Cara Menjalankan Test

Jalankan semua test bertag `@api`:

```bash
./gradlew apiTest
```

Jalankan semua test bertag `@web`:

```bash
./gradlew webTest
```

Jalankan Web UI test dengan browser terlihat:

```bash
./gradlew webTest -Dheadless=false
```

Jalankan semua Cucumber test:

```bash
./gradlew test
```

## API Authentication

DummyAPI membutuhkan header `app-id`. Framework ini membaca app id dari environment variable `DUMMY_API_APP_ID`. Jika tidak ada, framework memakai app id fallback yang disediakan pada instruksi project.

Contoh:

```bash
export DUMMY_API_APP_ID=your-app-id
./gradlew apiTest
```

Di PowerShell:

```powershell
$env:DUMMY_API_APP_ID="your-app-id"
./gradlew apiTest
```

## Reports

Cucumber report dibuat dalam format HTML dan JSON:

- `build/reports/cucumber/api-test-report.html`
- `build/reports/cucumber/api-test-report.json`
- `build/reports/cucumber/web-test-report.html`
- `build/reports/cucumber/web-test-report.json`

## GitHub Actions

Workflow berada di `.github/workflows/automation-tests.yml`.

Workflow berjalan saat:

- Pull Request dibuat atau diperbarui
- Manual trigger melalui `workflow_dispatch`

Workflow menjalankan:

- `./gradlew apiTest`
- `./gradlew webTest -Dheadless=true`

Report Cucumber diupload sebagai artifact bernama `cucumber-reports`.

## Catatan Implementasi

- Web UI menggunakan Page Object Model pada package `web.pages`.
- Step definitions Web UI dan API dipisah pada package `web.steps` dan `api.steps`.
- Feature file Web UI dan API dipisah pada folder `features/web` dan `features/api`.
- Validasi API response dilakukan dengan pengecekan status code, field penting pada JSON response, user id hasil create/get/delete, dan isi list tag.

## Membuat Repository GitHub

Setelah repository lokal siap, buat repository baru di GitHub lalu jalankan:

```bash
git remote add origin https://github.com/<username>/<repository-name>.git
git branch -M main
git push -u origin main
```
