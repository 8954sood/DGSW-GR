
def searchUserResponse(data: dict) -> dict:
    basic = data["summoner_basic_info_dict"]
    tier = data["season_tier_info_dict"]["ranked_solo_5x5"]
    tier_str = f"{tier['tier'] if tier['tier'] != '' else 'Unranked'} {tier['division'] if tier['division'] != 0 else ''}"
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
        "name":basic["summoner_name"], 
        "level": basic["level"], 
        "profile_id": basic["profile_id"],
        "tier_str": tier_str.replace(" ", "") if tier_str == "Unranked " else tier_str,
        "tier_int": TIER_DICT[tier['tier']]+tier['division']
    }
    return result
    