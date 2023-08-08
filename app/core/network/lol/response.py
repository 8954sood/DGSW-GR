import os
from bs4 import BeautifulSoup

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
        data["most"].append(most)
    for i in range(3-len(champion_conatiner)):
        data["most"].append({"kda": "0", "icon": "?", "win_rate": "0%"})

    
    

    solo = soup.find("div", {"class": ["css-1kw4425"]}).select_one("div[class=content]")
    if not solo is None:
        tier_icon = solo.find("img")["alt"].lower()
        tier_icon = f"https://opgg-static.akamaized.net/images/medals_new/{tier_icon}.png"
        
        tier_info = solo.select_one("div[class=info]")
        tier_str = tier_info.select_one("div[class=tier]").text
        tier_str = tier_str[0].upper() + tier_str[1:]
        tier_point = tier_info.select_one("div[class=lp]").text.replace(" ", "")
        

        win_lose_container = solo.select_one("div[class=win-lose-container]")
        win_lose = win_lose_container.select_one("div[class=win-lose]").text.replace("W", "승").replace("L", "패")
        win_rate = win_lose_container.select_one("div[class=ratio]").text.replace("Win Rate ", "")
        print(tier_icon)
        print(win_lose, win_rate)
        print(tier_str, tier_point)
        data["tier"] = tier_str + tier_point
        data["tier_icon"] = tier_icon
        data["win_lose"] = win_lose
        data["win_rate"] = win_rate
    else:
        data["tier"] = "Unranked"
        data["tier_icon"] = "http://localhost:8000/static/image/Unranked.png"
        data["win_lose"] = "0승 0패"
        data["win_rate"] = "0%"
    return data
