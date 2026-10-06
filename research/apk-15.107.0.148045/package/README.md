# Исходный пакет Mail.ru 15.107.0.148045

Пакет разбит на части по 45 МиБ, чтобы хранить его в GitHub.

Восстановление:

```bash
cat parts/Mail_ru_15.107.0.148045_APKs.zip.part-* > Mail_ru_15.107.0.148045_APKs.zip
sha256sum -c <(head -n 1 SHA256SUMS.txt)
```

Исходный пакет: `ru.mail.mailapp`, версия `15.107.0.148045`.
