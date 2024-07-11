Реализовал серверную часть приложения "Сервис перевода денег"

docker build -t transfermoneyservice:1.0 .
docker run -itd --name tms -p 5500:5500 transfermoneyservice:1.0

Пример запроса:
POST http://localhost:5500/transfer
{
    "cardFromNumber": "1795 5678 9982 3456",
    "cardFromValidTill": "12/24",
    "cardFromCVV": "123",
    "cardToNumber": "9886 5432 1988 7654",
    "amount": {
        "value": 200,
        "currency": "RU"
    }
}

Ответ приходит с индентификатором операции
