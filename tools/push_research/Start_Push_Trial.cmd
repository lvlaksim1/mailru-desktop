@echo off
chcp 65001 >nul
setlocal EnableExtensions DisableDelayedExpansion
cd /d "%~dp0"
title MailRu Desktop - Проверка новых писем
echo.
echo Проверка доставки уведомлений MailRu Desktop (эксперимент).
echo Установлены только исходные параметры официального мобильного APK.
echo Регистрация нового почтового приложения Mail.ru не выполняется.
echo.
echo Будет использован ТОЛЬКО выбранный уже подключенный почтовый аккаунт.
echo Программа не запрашивает пароль и не печатает почтовые токены.
echo Временная подписка нового получателя будет снята после проверки.
echo.
if not exist "MailRuPushTrial.exe" (
  echo Не найден MailRuPushTrial.exe в этой же папке.
  pause
  exit /b 1
)
set /p "TRIAL_LOGIN=Введите адрес тестового почтового аккаунта: "
if "%TRIAL_LOGIN%"=="" (
  echo Аккаунт не выбран.
  pause
  exit /b 1
)
echo.
echo Следующий шаг изменит подписку уведомлений ТОЛЬКО выбранного аккаунта.
echo Для продолжения введите слово YES.
set /p "TRIAL_CONFIRM=Подтверждение: "
if /I not "%TRIAL_CONFIRM%"=="YES" (
  echo Проверка отменена.
  pause
  exit /b 0
)
echo.
MailRuPushTrial.exe --live --subscribe --login "%TRIAL_LOGIN%" --seconds 180
set "RESULT=%ERRORLEVEL%"
echo.
echo Завершено, код результата: %RESULT%
echo Для повторной проверки требуется новая временная регистрация Google.
pause
exit /b %RESULT%
