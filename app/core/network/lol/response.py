import os
from bs4 import BeautifulSoup

TIER_DICT = {
    "": 0,
    "IRON": 10,
    "BRONZE": 20,
    "SILVER": 30,
    "GOLD": 40,
    "PLATINUM": 50,
    "EMERALD": 60,
    "DIAMOND": 70,
    "MASTER": 80,
    "GRANDMASTER": 90,
    "CHALLENGER": 10
}

TIER_INT_DICT = {
    "1": 3,
    "2": 2,
    "3": 1,
    "4": 0
}

URL = os.environ.get("URL")
PATH = "/api/file/static/image"
def searchUserResponse(data: dict) -> dict:
    basic = data["summoner_basic_info_dict"]
    tier = data["season_tier_info_dict"]["ranked_solo_5x5"]
    tier_str = f"{tier['tier'] if tier['tier'] != '' else 'Unranked'} {tier['division'] if tier['division'] != 0 else ''}"
    LOL_VERSION = os.getenv('LOL_VERSION')
    TIER_DICT = {
        "": 0,
        "IRON": 10,
        "BRONZE": 20,
        "SILVER": 30,
        "GOLD": 40,
        "PLATINUM": 50,
        "DIAMOND": 60,
        "MASTER": 70,
        "GRANDMASTER": 80,
        "CHALLENGER": 90
    }
    result = {
        "puu_id": basic["puu_id"],
        "nickname":basic["summoner_name"], 
        "level": basic["level"], 
        "profile_id": basic["profile_id"],
        "profile_icon": f"https://deeplol-ddragon-cdn.deeplol.gg/cdn/{LOL_VERSION}/img/profileicon/{basic['profile_id']}.png",
        "tier_str": tier_str.replace(" ", "") if tier_str == "Unranked " else tier_str,
        "tier_int": TIER_DICT[tier['tier']]+tier['division']
    }
    return result

def lol_parser(soup: BeautifulSoup) -> dict | bool:
    data = {}
    profile = soup.select_one("div[class=header-profile-info]")
    if profile is None:
        return False
    
    icon_container = profile.select_one("div[class=profile-icon]")
    data["icon"] = icon_container.find("img")["src"].split("?")[0]
    data["level"] = icon_container.select_one("span[class=level]").text
    data["name"] = profile.select_one("h1[class=summoner-name]").text
    
    champion_conatiner = soup.select("div[class=champion-box]" ,limit=3)
    data["most"] = []
    data["kda"] = 0
    for i in champion_conatiner:
        most = {}
        # kda = i.select_one("div[class=enfvmur2]")
        kda = i.find("div", {"class": ["enfvmur2"]}).text
        win_rate = i.find("div", {"class": ["enfvmur3"]}).text
        champ = i.select_one("div[class=name]").select_one("a")
        champ_eng_name = champ['href'].split("/")[2]
        champ_eng_name = champ_eng_name[0].upper() + champ_eng_name[1:]
        icon = f"https://opgg-static.akamaized.net/meta/images/lol/champion/{champ_eng_name}.png"
        most = {"kda": kda.split(":")[0], "icon": icon, "win_rate": win_rate}
        data["kda"] += float(kda.split(":")[0])
        data["most"].append(most)
    data["kda"] = str(format(data["kda"]/len(champion_conatiner), ".2f"))
    for i in range(3-len(champion_conatiner)):
        data["most"].append(
            {
                "kda": "0", 
                "icon": f"{URL}{PATH}/User.png", 
                "win_rate": "0%"
            }
        )

    
    

    solo = soup.find("div", {"class": ["css-1kw4425"]}).select_one("div[class=content]")
    if not solo is None:
        tier_icon_str = solo.find("img")["alt"]
        tier_icon = f"https://opgg-static.akamaized.net/images/medals_new/{tier_icon_str.lower()}.png"
    
        tier_info = solo.select_one("div[class=info]")
        tier_str = tier_info.select_one("div[class=tier]").text
        tier_str = tier_str[0].upper() + tier_str[1:]
        tier_point = tier_info.select_one("div[class=lp]").text.replace(" ", "")
        
        tier_int_str = tier_str.split(" ")
        tier_int = TIER_DICT[tier_int_str[0].upper()] + TIER_INT_DICT[tier_int_str[1]] if len(tier_int_str) == 2 else 0
        
        win_lose_container = solo.select_one("div[class=win-lose-container]")
        win_lose = win_lose_container.select_one("div[class=win-lose]").text.replace("W", "승").replace("L", "패")
        win_rate = win_lose_container.select_one("div[class=ratio]").text.replace("Win Rate ", "")
    
        data["tier_str"] = tier_str
        data["tier_point"] = tier_point
        data["tier_int"] = tier_int
        data["tier_icon"] = tier_icon
        data["win_lose"] = win_lose
        data["win_rate"] = win_rate
    else:
        data["tier_str"] = "Unranked"
        data["tier_point"] = "0LP"
        data["tier_int"] = 0
        data["tier_icon"] = f"{URL}{PATH}/Unranked.png"
        data["win_lose"] = "0승 0패"
        data["win_rate"] = "0%"
    return data
"""
{
  "icon": "https://opgg-static.akamaized.net/meta/images/profile_icons/profileIcon26.jpg",
  "level": "139",
  "name": "바비호바",
  "most": [
    {
      "kda": "0.2",
      "icon": "https://opgg-static.akamaized.net/meta/images/lol/champion/Nunu.png",
      "win_rate": "0%"
    },
    {
      "kda": "0",
      "icon": "http://localhost:8000/api/file/static/image/User.png",
      "win_rate": "0%"
    },
    {
      "kda": "0",
      "icon": "http://localhost:8000/api/file/static/image/User.png",
      "win_rate": "0%"
    }
  ],
  "tier_str": "Unranked",
  "tier_point": "0LP",
  "tier_int": 0,
  "tier_icon": "http://localhost:8000/api/file/static/image/Unranked.png",
  "win_lose": "0승 0패",
  "win_rate": "0%"
}
"""