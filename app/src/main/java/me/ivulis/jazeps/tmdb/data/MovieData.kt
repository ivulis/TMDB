package me.ivulis.jazeps.tmdb.data

import me.ivulis.jazeps.tmdb.model.Movie

object MovieData {
    fun getMovieData(): List<Movie> {
        return listOf(
            Movie(
                title = "Inception",
                releaseDate = "2010-07-15",
                runtime = "148m",
                rating = "8.363",
                poster = "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg"
            ),
            Movie(
                title = "Interstellar",
                releaseDate = "2014-11-05",
                runtime = "169m",
                rating = "8.415",
                poster = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg"
            ),
            Movie(
                title = "Tenet",
                releaseDate = "2020-08-22",
                runtime = "150m",
                rating = "7.193",
                poster = "https://image.tmdb.org/t/p/w500/aCIFMriQh8rvhxpN1IWGgvH0Tlg.jpg"
            ),
            Movie(
                title = "Oppenheimer",
                releaseDate = "2023-07-19",
                runtime = "181m",
                rating = "8.259",
                poster = "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg"
            ),
            Movie(
                title = "The Prestige",
                releaseDate = "2006-10-19",
                runtime = "130m",
                rating = "8.203",
                poster = "https://image.tmdb.org/t/p/w500/Ag2B2KHKQPukjH7WutmgnnSNurZ.jpg"
            ),
            Movie(
                title = "Dunkirk",
                releaseDate = "2017-07-19",
                runtime = "107m",
                rating = "7.456",
                poster = "https://image.tmdb.org/t/p/w500/b4Oe15CGLL61Ped0RAS9JpqdmCt.jpg"
            ),
            Movie(
                title = "Insomnia",
                releaseDate = "2002-05-24",
                runtime = "118m",
                rating = "6.935",
                poster = "https://image.tmdb.org/t/p/w500/riVXh3EimGO0y5dgQxEWPRy5Itg.jpg"
            ),
            Movie(
                title = "Memento",
                releaseDate = "2000-10-11",
                runtime = "113m",
                rating = "8.188",
                poster = "https://image.tmdb.org/t/p/w500/yuNs09hvpHVU1cBTCAk9zxsL2oW.jpg"
            ),
            Movie(
                title = "The Dark Knight",
                releaseDate = "2008-07-14",
                runtime = "152m",
                rating = "8.511",
                poster = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
            ),
            Movie(
                title = "The Dark Knight Rises",
                releaseDate = "2012-07-16",
                runtime = "165m",
                rating = "7.778",
                poster = "https://image.tmdb.org/t/p/w500/hr0L2aueqlP2BYUblTTjmtn0hw4.jpg"
            ),
            Movie(
                title = "Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Alien, Flesh Eating, Hellbound, Zombified Living Dead Part 2",
                releaseDate = "1991-01-01",
                runtime = "96m",
                rating = "4.5",
                poster = "https://image.tmdb.org/t/p/w500/31tmRWVLk1hEq3TLAH2Uq8XtkG2.jpg"
            )
        )
    }
}
