# Як опублікувати лабораторну в GitHub

Проєкт містить локальний Git-репозиторій з готовими комітами. На GitHub його ще не опубліковано. Автор технічних підготовлених комітів — `Lab Assistant`, адреса `lab-assistant@example.invalid`; це не твій GitHub-акаунт. Готову історію можна запушити незалежно від цього автора. Власне ім’я та email налаштуй для наступних комітів.

## 1. Перевірити готову історію

В IntelliJ IDEA відкрий **Terminal** у корені проєкту:

```shell
git --version
git status
git log --oneline
```

Має бути гілка `main`, чистий робочий каталог та готові коміти. Якщо `git` не розпізнано, встанови Git з https://git-scm.com/downloads і перезапусти IDEA. Якщо `not a git repository` — перевір, що термінал відкрито в папці з `pom.xml` і розпаковано весь архів разом з `.git`. Не запускай повторно `git init` замість перевірки папки.

У IDEA може знадобитися **Settings → Version Control** → додати кореневу папку як **Git**. Для перегляду історії: вікно **Git → Log**.

## 2. Створити порожній репозиторій

На GitHub увійди у свій акаунт → **New repository**. Назва, наприклад, `oop-lab1-variant12`. Вибери видимість згідно з вимогами викладача; якщо таких вимог немає, можна Private. Не додавай README, .gitignore або ліцензію на GitHub: локальний репозиторій уже має свою історію. Натисни **Create repository** і скопіюй HTTPS-адресу.

## 3. Під’єднати та запушити

У терміналі IDEA підстав власну скопійовану адресу замість прикладу `YOUR_USERNAME`:

```shell
git remote add origin https://github.com/YOUR_USERNAME/oop-lab1-variant12.git
git push -u origin main
```

Підтвердь вхід у GitHub, якщо відкриється браузер. За запитом пароля у терміналі звичайний пароль GitHub для HTTPS Git не працює: використовуй авторизацію через Git Credential Manager / браузер або token. Не додавай token у URL, файли проєкту чи коміти.

Якщо `origin already exists`, спочатку виконай `git remote -v` і перевір адресу. Якщо неправильна:

```shell
git remote set-url origin https://github.com/YOUR_USERNAME/oop-lab1-variant12.git
git push -u origin main
```

Якщо GitHub відхиляє push, бо на сервері вже є окремий README-коміт, найпростіше для цієї інструкції створити інший порожній репозиторій і змінити адресу `origin`. Не застосовуй force push навмання.

Після успіху онови сторінку GitHub: мають бути `pom.xml`, `src`, `docs`, README та історія комітів. Лише після успішного push виконано пункт умови про віддалений репозиторій. Для приватного репозиторію надай викладачу доступ, якщо потрібно.

## 4. Наступні власні зміни

Один раз налаштуй дані саме в цьому проєкті; заміни приклади власними. Email можна взяти з GitHub **Settings → Emails**, у тому числі приватну адресу noreply:

```shell
git config user.name "Твоє ім’я"
git config user.email "твоя-адреса-з-GitHub"
```

Після зміни коду запусти Maven `verify`, потім:

```shell
git status
git add src docs README.md pom.xml
git commit -m "feat: add another demonstration tariff"
git push
```

Текст коміту має описувати реальну зміну; приклад вище доречний тільки після додавання тарифу. Комітити без змін не потрібно. `target`, `.idea`, `out`, `.iml` і скомпільовані `.class` ігноруються.

Джерело інструкції: GitHub Docs, Adding locally hosted code to GitHub; посилання в README, перевірено 12.10.2026.
