from telethon import TelegramClient
from telethon.errors import UsernameInvalidError, PeerIdInvalidError
from telethon.tl.functions.users import GetFullUserRequest
from telethon.tl.functions.channels import GetFullChannelRequest
import logging

# Настройка логгера
logging.basicConfig(
    filename="user_activity.log",
    level=logging.INFO,
    format="%(asctime)s - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S"
)

def log_user_activity(username, status):
    logging.info(f"{username} - {status}")

# Введите свои api_id и api_hash
api_id = 'your_api_id'  # Замените на ваш api_id
api_hash = 'your_api_hash'  # Замените на ваш api_hash

# Создайте клиент
client = TelegramClient('session_name', api_id, api_hash)

async def fetch_last_seen(usernames):
    for username in usernames:
        try:
            entity = await client.get_entity(username)
            if entity.broadcast or entity.megagroup or entity.gigagroup:
                # Если это группа или канал
                full_entity = await client(GetFullChannelRequest(entity))
                status = full_entity.chats[0].title
                log_user_activity(username, f"Название группы: {status}")
                print(f"{username} - Название группы: {status}")
            else:
                # Если это пользователь
                full_user = await client(GetFullUserRequest(entity))

                # Определение статуса "последний раз был в сети"
                status = "Неизвестно"
                if entity.status:
                    if hasattr(entity.status, 'was_online'):
                        status = entity.status.was_online.strftime('%Y-%m-%d %H:%M:%S')
                    elif hasattr(entity.status, 'expires'):  # Если пользователь онлайн
                        status = "Сейчас в сети"

                log_user_activity(username, status)
                print(f"{username} - {status}")
        except (UsernameInvalidError, PeerIdInvalidError):
            log_user_activity(username, "Некорректный юзернейм")
            print(f"{username} - Некорректный юзернейм")
        except Exception as e:
            log_user_activity(username, f"Ошибка: {str(e)}")
            print(f"{username} - Ошибка: {str(e)}")

async def main():
    # Чтение списка юзернеймов из файла
    try:
        with open('usernames.txt', 'r') as file:
            usernames = [line.strip() for line in file if line.strip()]
        await fetch_last_seen(usernames)
    except FileNotFoundError:
        print("Файл usernames.txt не найден. Пожалуйста, создайте файл со списком юзернеймов.")

# Запуск клиента и основного процесса
with client:
    client.loop.run_until_complete(main())
