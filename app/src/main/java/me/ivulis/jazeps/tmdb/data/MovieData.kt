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
                poster = "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg",
                description = "Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious."
            ),
            Movie(
                title = "Interstellar",
                releaseDate = "2014-11-05",
                runtime = "169m",
                rating = "8.415",
                poster = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                description = "The adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage."
            ),
            Movie(
                title = "Tenet",
                releaseDate = "2020-08-22",
                runtime = "150m",
                rating = "7.193",
                poster = "https://image.tmdb.org/t/p/w500/aCIFMriQh8rvhxpN1IWGgvH0Tlg.jpg",
                description = "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time."
            ),
            Movie(
                title = "Oppenheimer",
                releaseDate = "2023-07-19",
                runtime = "181m",
                rating = "8.259",
                poster = "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
                description = "The story of J. Robert Oppenheimer’s role in the development of the atomic bomb during World War II."
            ),
            Movie(
                title = "The Prestige",
                releaseDate = "2006-10-19",
                runtime = "130m",
                rating = "8.203",
                poster = "https://image.tmdb.org/t/p/w500/Ag2B2KHKQPukjH7WutmgnnSNurZ.jpg",
                description = "A mysterious story of two magicians whose intense rivalry leads them on a life-long battle for supremacy -- full of obsession, deceit and jealousy with dangerous and deadly consequences."
            ),
            Movie(
                title = "Dunkirk",
                releaseDate = "2017-07-19",
                runtime = "107m",
                rating = "7.456",
                poster = "https://image.tmdb.org/t/p/w500/b4Oe15CGLL61Ped0RAS9JpqdmCt.jpg",
                description = "The story of the miraculous evacuation of Allied soldiers from Belgium, Britain, Canada and France, who were cut off and surrounded by the German army from the beaches and harbour of Dunkirk between May 26th and June 4th 1940 during World War II."
            ),
            Movie(
                title = "Insomnia",
                releaseDate = "2002-05-24",
                runtime = "118m",
                rating = "6.935",
                poster = "https://image.tmdb.org/t/p/w500/riVXh3EimGO0y5dgQxEWPRy5Itg.jpg",
                description = "Two Los Angeles homicide detectives are dispatched to a northern town where the sun doesn't set to investigate the methodical murder of a local teen."
            ),
            Movie(
                title = "Memento",
                releaseDate = "2000-10-11",
                runtime = "113m",
                rating = "8.188",
                poster = "https://image.tmdb.org/t/p/w500/yuNs09hvpHVU1cBTCAk9zxsL2oW.jpg",
                description = "Leonard Shelby is tracking down the man who raped and murdered his wife. The difficulty of locating his wife's killer, however, is compounded by the fact that he suffers from a rare, untreatable form of short-term memory loss. Although he can recall details of life before his accident, Leonard cannot remember what happened fifteen minutes ago, where he's going, or why."
            ),
            Movie(
                title = "The Dark Knight",
                releaseDate = "2008-07-14",
                runtime = "152m",
                rating = "8.511",
                poster = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                description = "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker."
            ),
            Movie(
                title = "The Dark Knight Rises",
                releaseDate = "2012-07-16",
                runtime = "165m",
                rating = "7.778",
                poster = "https://image.tmdb.org/t/p/w500/hr0L2aueqlP2BYUblTTjmtn0hw4.jpg",
                description = "Following the death of District Attorney Harvey Dent, Batman assumes responsibility for Dent's crimes to protect the late attorney's reputation and is subsequently hunted by the Gotham City Police Department. Eight years later, Batman encounters the mysterious Selina Kyle and the villainous Bane, a new terrorist leader who overwhelms Gotham's finest. The Dark Knight resurfaces to protect a city that has branded him an enemy."
            ),
            Movie(
                title = "Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Alien, Flesh Eating, Hellbound, Zombified Living Dead Part 2",
                releaseDate = "1991-01-01",
                runtime = "96m",
                rating = "4.5",
                poster = "https://image.tmdb.org/t/p/w500/31tmRWVLk1hEq3TLAH2Uq8XtkG2.jpg",
                description = "The makers of this parody of \"Night of the Living Dead\" took George Romero's classic and wiped the soundtrack clean, then redubbed it with comedic dialogue."
            )
        )
    }
}
