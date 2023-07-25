import requests
import json

url = "https://renew.deeplol.gg/match/matches"

payload = json.dumps({
  "platform_id": "KR",
  "puu_id": "JWEiLo3lHGDnub-NtEPBWHWmgRtExoFTqNGltIWMRWhaaz4VgZZUkcYoJOSjJFo4x9RSFwWoY60-Dw",
  "season": 17,
  "queue_type": "ALL",
  "start_idx": 0,
  "count": 20
})
headers = {
  'Referer': 'https://www.deeplol.gg/summoner/KR/%EB%B0%94%EB%B9%84%ED%98%B8%EB%B0%94',
  'Content-Type': 'application/json'
}

response = requests.request("POST", url, headers=headers, data=payload)

print(response.text)
