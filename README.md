[![Build Status](https://github.com/AtalantaK/Final_Certification_v2/actions/workflows/maven.yml/badge.svg)](https://github.com/AtalantaK/Final_Certification_v2/actions)
[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-orange.svg)](https://maven.apache.org/)
[![Allure Report](https://img.shields.io/badge/Allure-Report-success.svg)](https://atalantak.github.io/Final_Certification_v2/)

# Итоговая аттестация #

## Описание проекта
Java-проект, разработанный для итоговой аттестации по курсу "Автоматизированное тестирование ПО на JAVA".  
Проект использует **JUnit 5** для тестирования, **Maven** для сборки и **Allure** для генерации отчётов о тестировании.  
Автоматическое тестирование и публикация отчётов настроены через **GitHub Actions**.

---

## 🧑‍💻 Автор
**AtalantaK**  
📍 [GitHub Profile](https://github.com/AtalantaK)

---

## ⚙️ Технологии
- ☕ **Java 17**
- 🧱 **Maven**
- 🧪 **JUnit 5**
- 📊 **Allure Report**
- 🤖 **GitHub Actions** (CI/CD)

---

## 🚀 Установка и запуск

### 1. Клонирование репозитория
```bash
git clone https://github.com/AtalantaK/Final_Certification_v2.git
cd Final_Certification_v2
```

### 2. Проверка установленных программ
```bash
java -version
mvn -version
allure --version
```

💡 Java версии 17+ и Maven версии 3.8+ обязательны.
<br>Для установки Allure: [Инструкция по установке](https://allurereport.org/docs/#_installing_a_commandline)

## 🧪 Запуск всех тестов локально
```bash
mvn clean test
```
После выполнения тестов отчёты появятся в:
```bash
target/allure-results/
```
## 📊 Генерация отчёта Allure
После запуска тестов сгенерируйте HTML-отчёт:
```bash
allure generate target/allure-results --clean -o target/allure-report
```
Откройте отчёт локально:
```bash
allure serve target/allure-results
```
Отчёт будет автоматически открыт в браузере.

## 🤖 CI/CD с GitHub Actions

Каждый ```push``` или ```pull request``` в ветку **master** автоматически запускает GitHub Actions workflow:
- Сборка проекта с помощью **Maven**
- Запуск всех **JUnit 5** тестов
- Генерация **Allure** отчёта
- Публикация отчёта в **GitHub Pages**

Статус выполнения можно проверить во вкладке **Actions** на GitHub.
<br>Публикуемый отчёт доступен по ссылке из настроек **Pages** репозитория.

## 📁 Структура проекта

```bash
Final_Certification_v2/
├── src/
│   ├── main/        # Исходный код приложения
│   └── test/        # Тестовые классы (JUnit 5)
├── target/
│   ├── allure-results/  # Результаты тестов для Allure
│   └── allure-report/   # Сгенерированный отчёт
├── pom.xml          # Maven-конфигурация
└── .github/
    └── workflows/   # GitHub Actions workflow
```

## 📝 Задание

1. Требуется разработать **фреймворк автотестов**.

### 🧰 Стек автоматизации (минимум)
- ☕ **Java**
- 🌐 Любой **HTTP-клиент**
- 🧪 **JUnit 5**
- 📊 **Allure**
- 🗄️ **JDBC** (можно использовать любую ORM)
- 🖥️ **Selenide / Selenium**

---

## ✅ Требования к проекту

1. Есть **инструкция**, как запустить тесты из командной строки.  
2. Написан **pipeline** для запуска автотестов (**GitHub Actions** / **Jenkins**).  
3. Понятная структура пакетов:
```src/test/java/
├── api/ # API-тесты
├── ui/ # UI-тесты
├── pages/ # Page Object модели
├── db/ # Работа с базой данных
├── api_client/ # Работа с API
├── config/ # Управление настройками
└── data/ # Управление тестовыми данными
```
4. Понятный, читаемый **Allure-отчёт**.  
5. Оформлен файл **README.md** (описание проекта, инструкция по запуску).  
6. Оформлен файл **.gitignore** — в репозитории нет лишних файлов и папок.  
7. В проекте присутствуют **примерные API и UI тесты**  
(например, из промежуточных этапов 3 и 4).  
8. Примерные тесты можно легко удалить и начать писать автотесты под свой проект.

---

## 📦 Формат сдачи работы

- 📎 Ссылка на репозиторий в **GitHub**  
- 📄 В файле **README.md** должна быть описана команда для запуска тестов (`maven` или `gradle`)  
> Тесты будут запускаться **через командную строку**
- 📂 На портал прикладывается **архив с репозиторием**
- 🖥️ Подготовлена **презентация по шаблону**

