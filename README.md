# homethings-boot
HomeThingsManager

Регистрация в приложении
POST /registration
Параметры:
email,login,password,password_confirmation

Авторизация в приложении
POST /login
Параметры:
login,password

Выйти из профиля в приложении
GET /logout

//БЕЗ АВТОРИЗАЦИИ НЕДОСТУПНО

Создать группу "Дома" в приложении
POST /home/create
Параметры:
title

Войти в группу "Дома" в приложении
POST /home/login
Параметры:
title

Выйти из "дома"
GET /home/logout

Получить JSON объект "Дома"
GET /home

