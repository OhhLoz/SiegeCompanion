package productions.pudl.siege.Adapter;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import productions.pudl.siege.Data.DetailedObjects.DetailedGameModeObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedGeneralObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedMainObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedOperatorObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedOperatorsObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedPlacementsObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedRankObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedRanksObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedStatObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedStatsObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralLastPlayedObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRankObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRanksObject;
import productions.pudl.siege.Fragment.Search.Search;

public class R6DBJSONAdapter {
    protected String userName;
    protected String platformName;
    private Search search;
    private RequestQueue mQueue;
    private ArrayList<GeneralObject> searchResult = new ArrayList<>();

    public R6DBJSONAdapter(Search search, String currUserNameSelected, String currPlatformSelected, RequestQueue mQueue) {
        this.search = search;
        setUserName(currUserNameSelected);
        setPlatformName(currPlatformSelected);
        setmQueue(mQueue);
        //ParsePlatform();
        //ParseGeneral();
        //ParseDetailed();
    }

    private void ParsePlatform() {
        switch (getPlatformName()) {
            case "uPlay":
                setPlatformName("pc");
                break;
            case "Xbox":
                setPlatformName("xbox");
                break;
            case "Playstation":
                setPlatformName("ps4");
        }
    }

    public void ParseGeneral()
    {
        String URL = "https://r6db.com/api/v2/players?name=" + getUserName() + "&platform=" + getPlatformName();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            searchResult.clear();
                            Log.v("JSONArray", response.toString());
                            //ArrayList<GeneralObject> generalObjectArrayList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject currObject = response.getJSONObject(i);

                                String id = currObject.getString("id");
                                String userId = currObject.getString("userId");

                                String platform = currObject.getString("platform");
                                int level = currObject.getInt("level");
                                String createdAt = currObject.getString("created_at");
                                String updatedAt = currObject.getString("updated_at");

                                JSONObject lastPlayed = currObject.getJSONObject("lastPlayed");
                                String casual = lastPlayed.getString("casual");
                                String ranked = lastPlayed.getString("ranked");
                                String lpDate = "null";
                                if (!lastPlayed.isNull("last_played"))
                                    lpDate = lastPlayed.getString("last_played");

                                GeneralLastPlayedObject lastPlayedObject = new GeneralLastPlayedObject(casual, ranked, lpDate);

                                String name = currObject.getString("name");

                                JSONObject ranks = currObject.getJSONObject("ranks");
                                JSONObject apac = ranks.getJSONObject("apac");

                                double MMR = apac.getDouble("mmr");
                                int Rank = apac.getInt("rank");

                                GeneralRankObject apacObject = new GeneralRankObject("apac", MMR, Rank);

                                JSONObject emea = ranks.getJSONObject("emea");

                                MMR = emea.getDouble("mmr");
                                Rank = emea.getInt("rank");

                                GeneralRankObject emeaObject = new GeneralRankObject("emea", MMR, Rank);

                                JSONObject ncsa = ranks.getJSONObject("ncsa");

                                MMR = ncsa.getDouble("mmr");
                                Rank = ncsa.getInt("rank");

                                GeneralRankObject ncsaObject = new GeneralRankObject("ncsa", MMR, Rank);

                                GeneralRanksObject ranksObject = new GeneralRanksObject(apacObject, emeaObject, ncsaObject);

                                JSONArray aliases = currObject.getJSONArray("aliases");

                                ArrayList<GeneralAliasObject> aliasArrayList = new ArrayList<>();
                                for (int j = 0; j < aliases.length(); j++) {
                                    JSONObject currAlias = aliases.getJSONObject(j);

                                    String aliasName = currAlias.getString("name");
                                    String aliasCreatedAt = currAlias.getString("created_at");

                                    aliasArrayList.add(new GeneralAliasObject(aliasName, aliasCreatedAt));
                                    //Add to list of aliases
                                }

                                GeneralAliasesObject generalAliasesObject = new GeneralAliasesObject(aliasArrayList);

                                GeneralObject generalObject = new GeneralObject(id, userId, platform, level, createdAt, updatedAt, lastPlayedObject, name, ranksObject, generalAliasesObject);
                                searchResult.add(generalObject);
                                Log.v("JSONArrayResult", generalObject.toString());
                            }
                            Log.v("JSON", "Reached end of parsing!");

                            search.updateListView(searchResult);
                            // Add to list of R6DBUsers
                            // Do something with data i.e store elsewhere
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-app-id", "175980a6-8ce2-449c-976b-e8cd9c05d86b");
                return headers;
            }
        };

        getmQueue().add(request);
    }

    public void ParseDetailed(String id) {
        String URL = "https://r6db.com/api/v2/players/"+id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            searchResult.clear();
                            Log.v("JSONArray", response.toString());

                            String id = response.getString("id");
                            String userId = response.getString("userId");
                            String platform = response.getString("platform");
                            int level = response.getInt("level");
                            String createdAt = response.getString("created_at");
                            String updatedAt = response.getString("updated_at");

                            JSONObject lastPlayed = response.getJSONObject("lastPlayed");

                            String lpcasual = lastPlayed.getString("casual");
                            String lpranked = lastPlayed.getString("ranked");
                            String lpDate = "null";
                            if (!lastPlayed.isNull("last_played"))
                                lpDate = lastPlayed.getString("last_played");


                            GeneralLastPlayedObject lastPlayedObject = new GeneralLastPlayedObject(lpcasual, lpranked, lpDate);

                            String name = response.getString("name");

                            //rank

                            JSONObject rankObject = response.getJSONObject("rank");

                            JSONObject apac = rankObject.getJSONObject("apac");

                            String region = apac.getString("region");
                            int wins = apac.getInt("wins");
                            int losses = apac.getInt("losses");
                            int abandons = apac.getInt("abandons");
                            double maxmmr = apac.getDouble("max_mmr");
                            double mmr = apac.getDouble("mmr");
                            int rank = apac.getInt("rank");
                            int maxrank = apac.getInt("max_rank");
                            double skill_mean = apac.getDouble("skill_mean");
                            double skill_stdev = apac.getDouble("skill_stdev");

                            DetailedRankObject APAC = new DetailedRankObject(region, wins, losses, abandons, maxmmr, mmr, rank, maxrank, skill_mean, skill_stdev);

                            JSONObject emea = rankObject.getJSONObject("emea");

                            region = emea.getString("region");
                            wins = emea.getInt("wins");
                            losses = emea.getInt("losses");
                            abandons = emea.getInt("abandons");
                            maxmmr = emea.getDouble("max_mmr");
                            mmr = emea.getDouble("mmr");
                            rank = emea.getInt("rank");
                            maxrank = emea.getInt("max_rank");
                            skill_mean = emea.getDouble("skill_mean");
                            skill_stdev = emea.getDouble("skill_stdev");

                            DetailedRankObject EMEA = new DetailedRankObject(region, wins, losses, abandons, maxmmr, mmr, rank, maxrank, skill_mean, skill_stdev);

                            JSONObject ncsa = rankObject.getJSONObject("ncsa");

                            region = ncsa.getString("region");
                            wins = ncsa.getInt("wins");
                            losses = ncsa.getInt("losses");
                            abandons = ncsa.getInt("abandons");
                            maxmmr = ncsa.getDouble("max_mmr");
                            mmr = ncsa.getDouble("mmr");
                            rank = ncsa.getInt("rank");
                            maxrank = ncsa.getInt("max_rank");
                            skill_mean = ncsa.getDouble("skill_mean");
                            skill_stdev = ncsa.getDouble("skill_stdev");

                            DetailedRankObject NCSA = new DetailedRankObject(region, wins, losses, abandons, maxmmr, mmr, rank, maxrank, skill_mean, skill_stdev);

                            int season = rankObject.getInt("season");

                            DetailedRanksObject ranksObject = new DetailedRanksObject(APAC, EMEA, NCSA, season);

                            //prevRanks
                            JSONArray prevRanksArr = response.getJSONArray("seasonRanks");
                            ArrayList<DetailedRanksObject> prevRanks = new ArrayList<>();
                            ArrayList<DetailedRankObject> tempRank = new ArrayList<>();
                            String[] ranks = new String[]{"apac", "emea", "ncsa"};

                            for (int i = 0; i < prevRanksArr.length(); i++)
                            {
                                JSONObject currRanksObj = prevRanksArr.getJSONObject(i);
                                tempRank.clear();

                                for(int j = 0; j < 3; j++)
                                {
                                    JSONObject currRanks = currRanksObj.getJSONObject(ranks[j]);

                                    mmr = currRanks.getDouble("mmr");
                                    rank = currRanks.getInt("rank");
                                    wins = currRanks.getInt("wins");
                                    losses = currRanks.getInt("losses");
                                    region = currRanks.getString("region");
                                    maxmmr = currRanks.getDouble("max_mmr");
                                    abandons = currRanks.getInt("abandons");
                                    maxrank = currRanks.getInt("max_rank");
                                    skill_mean = currRanks.getDouble("skill_mean");
                                    skill_stdev = currRanks.getDouble("skill_stdev");

                                    DetailedRankObject currRank = new DetailedRankObject(region, wins, losses, abandons, maxmmr, mmr, rank, maxrank, skill_mean, skill_stdev);
                                    tempRank.add(currRank);
                                }
                                season = currRanksObj.getInt("season");
                                prevRanks.add(new DetailedRanksObject(tempRank.get(0),tempRank.get(1), tempRank.get(2), season));
                            }

                            //stats
                            JSONObject statsObject = response.getJSONObject("stats");

                            JSONObject bombObject = statsObject.getJSONObject("bomb");
                            int bestScore = bombObject.getInt("bestScore");
                            int lost = bombObject.getInt("lost");
                            int played = bombObject.getInt("played");
                            int won = bombObject.getInt("won");

                            DetailedGameModeObject bombGMObject = new DetailedGameModeObject(bestScore, lost, played, won);

                            JSONObject hostageObject = statsObject.getJSONObject("hostage");
                            bestScore = hostageObject.getInt("bestScore");
                            lost = hostageObject.getInt("lost");
                            played = hostageObject.getInt("played");
                            won = hostageObject.getInt("won");

                            DetailedGameModeObject hostageGMObject = new DetailedGameModeObject(bestScore, lost, played, won);

                            JSONObject secureObject = statsObject.getJSONObject("hostage");
                            bestScore = secureObject.getInt("bestScore");
                            lost = secureObject.getInt("lost");
                            played = secureObject.getInt("played");
                            won = secureObject.getInt("won");

                            DetailedGameModeObject secureGMObject = new DetailedGameModeObject(bestScore, lost, played, won);

                            JSONObject casualObject = statsObject.getJSONObject("casual");
                            int deaths = casualObject.getInt("deaths");
                            int kills = casualObject.getInt("kills");
                            lost = casualObject.getInt("lost");
                            played = casualObject.getInt("played");
                            int timePlayed = 0;
                            if (casualObject.isNull("timePlayed"))
                                timePlayed = 0;
                            else
                                timePlayed = casualObject.getInt("timePlayed");
                            won = casualObject.getInt("won");

                            DetailedStatObject casual = new DetailedStatObject(deaths, kills, lost, played, timePlayed, won);

                            JSONObject rankedObject = statsObject.getJSONObject("ranked");
                            deaths = rankedObject.getInt("deaths");
                            kills = rankedObject.getInt("kills");
                            lost = rankedObject.getInt("lost");
                            played = rankedObject.getInt("played");
                            if(rankedObject.isNull("timePlayed"))
                                timePlayed = 0;
                            else
                                timePlayed = rankedObject.getInt("timePlayed");
                            won = rankedObject.getInt("won");

                            DetailedStatObject ranked = new DetailedStatObject(deaths, kills, lost, played, timePlayed, won);

                            JSONObject generalObject = statsObject.getJSONObject("general");

                            int assists = generalObject.getInt("assists");
                            int blindKills = generalObject.getInt("blindKills");
                            int bulletsFired = generalObject.getInt("bulletsFired");
                            int bulletsHit = generalObject.getInt("bulletsHit");
                            int dbno = generalObject.getInt("dbno");
                            int dbnoAssists = generalObject.getInt("dbnoAssists");
                            deaths = generalObject.getInt("deaths");
                            int gadgetsDestroyed = generalObject.getInt("gadgetsDestroyed");
                            int headshot = generalObject.getInt("headshot");
                            int hostageDefense = generalObject.getInt("hostageDefense");
                            int hostageRescue = generalObject.getInt("hostageRescue");
                            kills = generalObject.getInt("kills");
                            lost = generalObject.getInt("lost");
                            int meleeKills = generalObject.getInt("meleeKills");
                            int penetrationKills = generalObject.getInt("penetrationKills");
                            played = generalObject.getInt("played");
                            int rappelBreaches = generalObject.getInt("rappelBreaches");
                            int revives = generalObject.getInt("revives");
                            int revivesDenied = generalObject.getInt("revivesDenied");
                            int serverAggression = generalObject.getInt("serverAggression");
                            int serverDefender = generalObject.getInt("serverDefender");
                            int serversHacked = generalObject.getInt("serversHacked");
                            int suicides = generalObject.getInt("suicides");
                            timePlayed = generalObject.getInt("timePlayed");
                            won = generalObject.getInt("won");

                            DetailedGeneralObject general = new DetailedGeneralObject(assists, blindKills, bulletsFired, bulletsHit, dbno, dbnoAssists, deaths, gadgetsDestroyed, headshot, hostageDefense, hostageRescue, kills, lost, meleeKills, penetrationKills, played, rappelBreaches, revives, revivesDenied, serverAggression, serverDefender, serversHacked, suicides, timePlayed, won);


                            JSONObject operatorObject = statsObject.getJSONObject("operator");

                            JSONObject iqObject = operatorObject.getJSONObject("iq");
                            won = iqObject.getInt("won");
                            lost = iqObject.getInt("lost");
                            kills = iqObject.getInt("kills");
                            deaths = iqObject.getInt("deaths");
                            timePlayed = iqObject.getInt("timePlayed");
                            String opname = iqObject.getString("name");

                            DetailedOperatorObject iq = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GSG9");


                            JSONObject ashObject = operatorObject.getJSONObject("ash");
                            won = ashObject.getInt("won");
                            lost = ashObject.getInt("lost");
                            kills = ashObject.getInt("kills");
                            deaths = ashObject.getInt("deaths");
                            timePlayed = ashObject.getInt("timePlayed");
                            opname = ashObject.getString("name");

                            DetailedOperatorObject ash = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "FBI");

                            JSONObject docObject = operatorObject.getJSONObject("doc");
                            won = docObject.getInt("won");
                            lost = docObject.getInt("lost");
                            kills = docObject.getInt("kills");
                            deaths = docObject.getInt("deaths");
                            timePlayed = docObject.getInt("timePlayed");
                            opname = docObject.getString("name");

                            DetailedOperatorObject doc = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GIGN");

                            JSONObject elaObject = operatorObject.getJSONObject("ela");
                            won = elaObject.getInt("won");
                            lost = elaObject.getInt("lost");
                            kills = elaObject.getInt("kills");
                            deaths = elaObject.getInt("deaths");
                            timePlayed = elaObject.getInt("timePlayed");
                            opname = elaObject.getString("name");

                            DetailedOperatorObject ela = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GROM");

                            JSONObject buckObject = operatorObject.getJSONObject("buck");
                            won = buckObject.getInt("won");
                            lost = buckObject.getInt("lost");
                            kills = buckObject.getInt("kills");
                            deaths = buckObject.getInt("deaths");
                            timePlayed = buckObject.getInt("timePlayed");
                            opname = buckObject.getString("name");

                            DetailedOperatorObject buck = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "JTF2");

                            JSONObject echoObject = operatorObject.getJSONObject("echo");
                            won = echoObject.getInt("won");
                            lost = echoObject.getInt("lost");
                            kills = echoObject.getInt("kills");
                            deaths = echoObject.getInt("deaths");
                            timePlayed = echoObject.getInt("timePlayed");
                            opname = echoObject.getString("name");

                            DetailedOperatorObject echo = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SAT");

                            JSONObject fuzeObject = operatorObject.getJSONObject("fuze");
                            won = fuzeObject.getInt("won");
                            lost = fuzeObject.getInt("lost");
                            kills = fuzeObject.getInt("kills");
                            deaths = fuzeObject.getInt("deaths");
                            timePlayed = fuzeObject.getInt("timePlayed");
                            opname = fuzeObject.getString("name");

                            DetailedOperatorObject fuze = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SPETSNAZ");

                            JSONObject glazObject = operatorObject.getJSONObject("glaz");
                            won = glazObject.getInt("won");
                            lost = glazObject.getInt("lost");
                            kills = glazObject.getInt("kills");
                            deaths = glazObject.getInt("deaths");
                            timePlayed = glazObject.getInt("timePlayed");
                            opname = glazObject.getString("name");

                            DetailedOperatorObject glaz = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SPETSNAZ");

                            JSONObject lionObject = operatorObject.getJSONObject("lion");
                            won = lionObject.getInt("won");
                            lost = lionObject.getInt("lost");
                            kills = lionObject.getInt("kills");
                            deaths = lionObject.getInt("deaths");
                            timePlayed = lionObject.getInt("timePlayed");
                            opname = lionObject.getString("name");

                            DetailedOperatorObject lion = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "CBRN");

                            JSONObject miraObject = operatorObject.getJSONObject("mira");
                            won = miraObject.getInt("won");
                            lost = miraObject.getInt("lost");
                            kills = miraObject.getInt("kills");
                            deaths = miraObject.getInt("deaths");
                            timePlayed = miraObject.getInt("timePlayed");
                            opname = miraObject.getString("name");

                            DetailedOperatorObject mira = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GEO");

                            JSONObject muteObject = operatorObject.getJSONObject("mute");
                            won = muteObject.getInt("won");
                            lost = muteObject.getInt("lost");
                            kills = muteObject.getInt("kills");
                            deaths = muteObject.getInt("deaths");
                            timePlayed = muteObject.getInt("timePlayed");
                            opname = muteObject.getString("name");

                            DetailedOperatorObject mute = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SAS");

                            JSONObject rookObject = operatorObject.getJSONObject("rook");
                            won = rookObject.getInt("won");
                            lost = rookObject.getInt("lost");
                            kills = rookObject.getInt("kills");
                            deaths = rookObject.getInt("deaths");
                            timePlayed = rookObject.getInt("timePlayed");
                            opname = rookObject.getString("name");

                            DetailedOperatorObject rook = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GIGN");

                            JSONObject yingObject = operatorObject.getJSONObject("ying");
                            won = yingObject.getInt("won");
                            lost = yingObject.getInt("lost");
                            kills = yingObject.getInt("kills");
                            deaths = yingObject.getInt("deaths");
                            timePlayed = yingObject.getInt("timePlayed");
                            opname = yingObject.getString("name");

                            DetailedOperatorObject ying = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SDU");

                            JSONObject blitzObject = operatorObject.getJSONObject("blitz");
                            won = blitzObject.getInt("won");
                            lost = blitzObject.getInt("lost");
                            kills = blitzObject.getInt("kills");
                            deaths = blitzObject.getInt("deaths");
                            timePlayed = blitzObject.getInt("timePlayed");
                            opname = blitzObject.getString("name");

                            DetailedOperatorObject blitz = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GSG9");

                            JSONObject finkaObject = operatorObject.getJSONObject("finka");
                            won = finkaObject.getInt("won");
                            lost = finkaObject.getInt("lost");
                            kills = finkaObject.getInt("kills");
                            deaths = finkaObject.getInt("deaths");
                            timePlayed = finkaObject.getInt("timePlayed");
                            opname = finkaObject.getString("name");

                            DetailedOperatorObject finka = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "CBRN");

                            JSONObject frostObject = operatorObject.getJSONObject("frost");
                            won = frostObject.getInt("won");
                            lost = frostObject.getInt("lost");
                            kills = frostObject.getInt("kills");
                            deaths = frostObject.getInt("deaths");
                            timePlayed = frostObject.getInt("timePlayed");
                            opname = frostObject.getString("name");

                            DetailedOperatorObject frost = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "JTF2");

                            JSONObject jagerObject = operatorObject.getJSONObject("jager");
                            won = jagerObject.getInt("won");
                            lost = jagerObject.getInt("lost");
                            kills = jagerObject.getInt("kills");
                            deaths = jagerObject.getInt("deaths");
                            timePlayed = jagerObject.getInt("timePlayed");
                            opname = jagerObject.getString("name");

                            DetailedOperatorObject jager = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GSG9");

                            JSONObject pulseObject = operatorObject.getJSONObject("pulse");
                            won = pulseObject.getInt("won");
                            lost = pulseObject.getInt("lost");
                            kills = pulseObject.getInt("kills");
                            deaths = pulseObject.getInt("deaths");
                            timePlayed = pulseObject.getInt("timePlayed");
                            opname = pulseObject.getString("name");

                            DetailedOperatorObject pulse = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "FBI");

                            JSONObject smokeObject = operatorObject.getJSONObject("smoke");
                            won = smokeObject.getInt("won");
                            lost = smokeObject.getInt("lost");
                            kills = smokeObject.getInt("kills");
                            deaths = smokeObject.getInt("deaths");
                            timePlayed = smokeObject.getInt("timePlayed");
                            opname = smokeObject.getString("name");

                            DetailedOperatorObject smoke = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SAS");

                            JSONObject vigilObject = operatorObject.getJSONObject("vigil");
                            won = vigilObject.getInt("won");
                            lost = vigilObject.getInt("lost");
                            kills = vigilObject.getInt("kills");
                            deaths = vigilObject.getInt("deaths");
                            timePlayed = vigilObject.getInt("timePlayed");
                            opname = vigilObject.getString("name");

                            DetailedOperatorObject vigil = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SMB");

                            JSONObject zofiaObject = operatorObject.getJSONObject("zofia");
                            won = zofiaObject.getInt("won");
                            lost = zofiaObject.getInt("lost");
                            kills = zofiaObject.getInt("kills");
                            deaths = zofiaObject.getInt("deaths");
                            timePlayed = zofiaObject.getInt("timePlayed");
                            opname = zofiaObject.getString("name");

                            DetailedOperatorObject zofia = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GROM");

                            JSONObject banditObject = operatorObject.getJSONObject("bandit");
                            won = banditObject.getInt("won");
                            lost = banditObject.getInt("lost");
                            kills = banditObject.getInt("kills");
                            deaths = banditObject.getInt("deaths");
                            timePlayed = banditObject.getInt("timePlayed");
                            opname = banditObject.getString("name");

                            DetailedOperatorObject bandit = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GSG9");

                            JSONObject castleObject = operatorObject.getJSONObject("castle");
                            won = castleObject.getInt("won");
                            lost = castleObject.getInt("lost");
                            kills = castleObject.getInt("kills");
                            deaths = castleObject.getInt("deaths");
                            timePlayed = castleObject.getInt("timePlayed");
                            opname = castleObject.getString("name");

                            DetailedOperatorObject castle = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "FBI");

                            JSONObject hibanaObject = operatorObject.getJSONObject("hibana");
                            won = hibanaObject.getInt("won");
                            lost = hibanaObject.getInt("lost");
                            kills = hibanaObject.getInt("kills");
                            deaths = hibanaObject.getInt("deaths");
                            timePlayed = hibanaObject.getInt("timePlayed");
                            opname = hibanaObject.getString("name");

                            DetailedOperatorObject hibana = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SAT");

                            JSONObject jackalObject = operatorObject.getJSONObject("jackal");
                            won = jackalObject.getInt("won");
                            lost = jackalObject.getInt("lost");
                            kills = jackalObject.getInt("kills");
                            deaths = jackalObject.getInt("deaths");
                            timePlayed = jackalObject.getInt("timePlayed");
                            opname = jackalObject.getString("name");

                            DetailedOperatorObject jackal = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GEO");

                            JSONObject kapkanObject = operatorObject.getJSONObject("kapkan");
                            won = kapkanObject.getInt("won");
                            lost = kapkanObject.getInt("lost");
                            kills = kapkanObject.getInt("kills");
                            deaths = kapkanObject.getInt("deaths");
                            timePlayed = kapkanObject.getInt("timePlayed");
                            opname = kapkanObject.getString("name");

                            DetailedOperatorObject kapkan = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SPETSNAZ");

                            JSONObject lesionObject = operatorObject.getJSONObject("lesion");
                            won = lesionObject.getInt("won");
                            lost = lesionObject.getInt("lost");
                            kills = lesionObject.getInt("kills");
                            deaths = lesionObject.getInt("deaths");
                            timePlayed = lesionObject.getInt("timePlayed");
                            opname = lesionObject.getString("name");

                            DetailedOperatorObject lesion = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SDU");

                            JSONObject sledgeObject = operatorObject.getJSONObject("sledge");
                            won = sledgeObject.getInt("won");
                            lost = sledgeObject.getInt("lost");
                            kills = sledgeObject.getInt("kills");
                            deaths = sledgeObject.getInt("deaths");
                            timePlayed = sledgeObject.getInt("timePlayed");
                            opname = sledgeObject.getString("name");

                            DetailedOperatorObject sledge = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SAS");

                            JSONObject twitchObject = operatorObject.getJSONObject("twitch");
                            won = twitchObject.getInt("won");
                            lost = twitchObject.getInt("lost");
                            kills = twitchObject.getInt("kills");
                            deaths = twitchObject.getInt("deaths");
                            timePlayed = twitchObject.getInt("timePlayed");
                            opname = twitchObject.getString("name");

                            DetailedOperatorObject twitch = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GIGN");

                            JSONObject capitaoObject = operatorObject.getJSONObject("capitao");
                            won = capitaoObject.getInt("won");
                            lost = capitaoObject.getInt("lost");
                            kills = capitaoObject.getInt("kills");
                            deaths = capitaoObject.getInt("deaths");
                            timePlayed = capitaoObject.getInt("timePlayed");
                            opname = capitaoObject.getString("name");

                            DetailedOperatorObject capitao = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "BOPE");

                            JSONObject caveiraObject = operatorObject.getJSONObject("caveira");
                            won = caveiraObject.getInt("won");
                            lost = caveiraObject.getInt("lost");
                            kills = caveiraObject.getInt("kills");
                            deaths = caveiraObject.getInt("deaths");
                            timePlayed = caveiraObject.getInt("timePlayed");
                            opname = caveiraObject.getString("name");

                            DetailedOperatorObject caveira = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "BOPE");

                            JSONObject dokkaebiObject = operatorObject.getJSONObject("dokkaebi");
                            won = dokkaebiObject.getInt("won");
                            lost = dokkaebiObject.getInt("lost");
                            kills = dokkaebiObject.getInt("kills");
                            deaths = dokkaebiObject.getInt("deaths");
                            timePlayed = dokkaebiObject.getInt("timePlayed");
                            opname = dokkaebiObject.getString("name");

                            DetailedOperatorObject dokkaebi = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SMB");

                            JSONObject montagneObject = operatorObject.getJSONObject("montagne");
                            won = montagneObject.getInt("won");
                            lost = montagneObject.getInt("lost");
                            kills = montagneObject.getInt("kills");
                            deaths = montagneObject.getInt("deaths");
                            timePlayed = montagneObject.getInt("timePlayed");
                            opname = montagneObject.getString("name");

                            DetailedOperatorObject montagne = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "GIGN");

                            JSONObject tachankaObject = operatorObject.getJSONObject("tachanka");
                            won = tachankaObject.getInt("won");
                            lost = tachankaObject.getInt("lost");
                            kills = tachankaObject.getInt("kills");
                            deaths = tachankaObject.getInt("deaths");
                            timePlayed = tachankaObject.getInt("timePlayed");
                            opname = tachankaObject.getString("name");

                            DetailedOperatorObject tachanka = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SPETSNAZ");

                            JSONObject thatcherObject = operatorObject.getJSONObject("thatcher");
                            won = thatcherObject.getInt("won");
                            lost = thatcherObject.getInt("lost");
                            kills = thatcherObject.getInt("kills");
                            deaths = thatcherObject.getInt("deaths");
                            timePlayed = thatcherObject.getInt("timePlayed");
                            opname = thatcherObject.getString("name");

                            DetailedOperatorObject thatcher = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SAS");

                            JSONObject thermiteObject = operatorObject.getJSONObject("thermite");
                            won = thermiteObject.getInt("won");
                            lost = thermiteObject.getInt("lost");
                            kills = thermiteObject.getInt("kills");
                            deaths = thermiteObject.getInt("deaths");
                            timePlayed = thermiteObject.getInt("timePlayed");
                            opname = thermiteObject.getString("name");

                            DetailedOperatorObject thermite = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "FBI");

                            JSONObject valkyrieObject = operatorObject.getJSONObject("valkyrie");
                            won = valkyrieObject.getInt("won");
                            lost = valkyrieObject.getInt("lost");
                            kills = valkyrieObject.getInt("kills");
                            deaths = valkyrieObject.getInt("deaths");
                            timePlayed = valkyrieObject.getInt("timePlayed");
                            opname = valkyrieObject.getString("name");

                            DetailedOperatorObject valkyrie = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SEALS");

                            JSONObject blackbeardObject = operatorObject.getJSONObject("blackbeard");
                            won = blackbeardObject.getInt("won");
                            lost = blackbeardObject.getInt("lost");
                            kills = blackbeardObject.getInt("kills");
                            deaths = blackbeardObject.getInt("deaths");
                            timePlayed = blackbeardObject.getInt("timePlayed");
                            opname = blackbeardObject.getString("name");

                            DetailedOperatorObject blackbeard = new DetailedOperatorObject(won, lost, kills, deaths, timePlayed, name, "SEALS");

                            DetailedOperatorsObject operatorsObject = new DetailedOperatorsObject(iq, ash, doc, ela, buck, echo, fuze, glaz, lion, mira, mute, rook, ying, blitz, finka, frost, jager, pulse, smoke, vigil, zofia, bandit, castle, hibana, jackal, kapkan, lesion, sledge, twitch, capitao, caveira, dokkaebi, montagne, tachanka, thatcher, thermite, valkyrie, blackbeard);

                            DetailedStatsObject detailedStatsObject = new DetailedStatsObject(bombGMObject, casual, general, hostageGMObject, operatorsObject, ranked, secureGMObject);
                            //placements

                            JSONObject placements = response.getJSONObject("placements");

                            DetailedPlacementsObject placementsObject = null;
                            int globalPlacement = 0;
                            int apacPlacement = 0;
                            int emeaPlacement = 0;
                            int ncsaPlacement = 0;

                            if(!placements.isNull("global"))
                            {
                                globalPlacement = placements.getInt("global");
                                if (!placements.isNull("apac"))
                                    apacPlacement = placements.getInt("apac");
                                if (!placements.isNull("emea"))
                                    emeaPlacement = placements.getInt("emea");
                                if (!placements.isNull("ncsa"))
                                    ncsaPlacement = placements.getInt("ncsa");
                            }

                            placementsObject = new DetailedPlacementsObject(globalPlacement, apacPlacement, emeaPlacement, ncsaPlacement);


                            //aliases

                            JSONArray aliases = response.getJSONArray("aliases");

                            ArrayList<GeneralAliasObject> aliasArrayList = new ArrayList<>();
                            for (int j = 0; j < aliases.length(); j++) {
                                JSONObject currAlias = aliases.getJSONObject(j);

                                String aliasName = currAlias.getString("name");
                                String aliasCreatedAt = currAlias.getString("created_at");

                                aliasArrayList.add(new GeneralAliasObject(aliasName, aliasCreatedAt));
                                //Add to list of aliases
                            }

                            GeneralAliasesObject generalAliasesObject = new GeneralAliasesObject(aliasArrayList);

                            String serverTime = response.getString("serverTime");
                            String updateTime = response.getString("updateAvailableAt");

                            Log.v("JSON", "Reached end of parsing!");

                            DetailedMainObject finalObject = new DetailedMainObject(id, userId, platform, level, createdAt, updatedAt, lastPlayedObject, name, ranksObject, prevRanks, detailedStatsObject, placementsObject, generalAliasesObject, serverTime, updateTime);

                            search.updateFinalResult(finalObject);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-app-id", "175980a6-8ce2-449c-976b-e8cd9c05d86b");
                return headers;
            }
        };

        getmQueue().add(request);
    }

    public String getPlatformName() {
        return this.platformName;
    }

    public void setPlatformName(String platform) {
        this.platformName = platform;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    private RequestQueue getmQueue() {
        return mQueue;
    }

    private void setmQueue(RequestQueue mQueue) {
        this.mQueue = mQueue;
    }

    public ArrayList<GeneralObject> getSearchResults() {
        return this.searchResult;
    }

}

