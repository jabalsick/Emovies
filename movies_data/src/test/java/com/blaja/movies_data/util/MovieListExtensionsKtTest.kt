package com.blaja.movies_data.util

import com.blaja.movies_data.TestConstants.EMPTY_MOVIE_ITEM_LIST
import com.blaja.movies_data.TestConstants.LANGUAGES
import com.blaja.movies_data.TestConstants.MOVIE_ITEM_LIST
import com.blaja.movies_data.TestConstants.MULTIPLE_LANGUAGES_MOVIE_ITEM_LIST
import com.blaja.movies_data.TestConstants.SPANISH_LANGUAGE
import com.blaja.movies_data.filterRecommendedMoviesByLanguage
import com.blaja.movies_data.getLanguages
import com.blaja.movies_data.limitMoviesQuantity
import com.blaja.movies_data.model.MovieItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieListExtensionsKtTest{

    private lateinit var subject: List<MovieItem>

    private fun givenASetUpSubject(value : List<MovieItem>) {
        subject = value
    }

    @Test
    fun `GIVEN a movie List WHEN languages are requested THEN the result is a list of different languages`() {
        givenASetUpSubject(MOVIE_ITEM_LIST)

        val result = subject.getLanguages()

        assertThat(result).isEqualTo(LANGUAGES)
    }

    @Test
    fun `GIVEN an empty movie List WHEN languages are requested THEN the result is an empty list of languages`() {
        givenASetUpSubject(EMPTY_MOVIE_ITEM_LIST)

        val result = subject.getLanguages()

        assertThat(result).isEqualTo(listOf<String>())
    }

    @Test
    fun `GIVEN a movie List with repeated languages WHEN languages are requested THEN the result is a list of different languages`() {
        givenASetUpSubject(MULTIPLE_LANGUAGES_MOVIE_ITEM_LIST)

        val result = subject.getLanguages()

        assertThat(result).containsNoDuplicates()
    }

    @Test
    fun `GIVEN a list with more than 6 movies WHEN recommended movies are requested THEN the result is a list of 6 movies`() {
        givenASetUpSubject(MULTIPLE_LANGUAGES_MOVIE_ITEM_LIST)

        val result = subject.limitMoviesQuantity()

        assertThat(result).hasSize(6)
    }

    @Test
    fun `GIVEN a list with less than 6 movies WHEN recommended movies are requested THEN the result is a list of movies`() {
        givenASetUpSubject(MOVIE_ITEM_LIST)

        val result = subject.limitMoviesQuantity()

        assertThat(result).hasSize(subject.size)
    }

    @Test
    fun `GIVEN a movie list with different languages WHEN recommended movies in spanish are requested THEN the result is a list of movies in spanish`() {
        givenASetUpSubject(MULTIPLE_LANGUAGES_MOVIE_ITEM_LIST)

        val result = subject.filterRecommendedMoviesByLanguage(SPANISH_LANGUAGE)

        for(item in result){
            assertThat(item.originalLanguage?.uppercase()).isEqualTo(SPANISH_LANGUAGE)
        }
    }
}