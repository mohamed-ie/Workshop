package com.example.news.news.model.repository



import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.runner.AndroidJUnit4
import com.example.news.MainCoroutineRule
import com.example.news.news.model.source.local.FakeNewsLocalDataSource
import com.example.news.news.model.source.local.entitiy.Article
import com.example.news.news.model.source.remote.FakeNewsRemoteDataSource
import com.example.news.news.model.source.remote.dto.NewsDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*


class NewsRepositoryImplTest {

    private lateinit var newsRepository: NewsRepository
    private lateinit var articleList:MutableList<Article>

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun after(){
        articleList = mutableListOf()
        newsRepository = NewsRepositoryImpl(FakeNewsRemoteDataSource(),FakeNewsLocalDataSource(articleList))
    }

    @Test
    fun getNews_noInput_newsDtoObject() = runTest{
        //Given
        val actual = NewsDTO(0, mutableListOf(),"anyThing")
        //When
        val expected = newsRepository.getNews("us").first()
        //Then
       assertNotEquals(expected,actual)
    }

    @Test
    fun saveFavoriteNews_article_savedArticle() = runTest{
        //When
        newsRepository.saveFavoriteNews(Article("","",
            "","","","","","",true,false))
        val expected = newsRepository.getFavouriteNews().first()
        //Then
        assertEquals(expected.count(),1)
    }

    @Test
    fun deleteFavourite() = runTest{
        //Given
        val article = Article("","",
            "","","","","","",true,false)
        //When
        newsRepository.saveFavoriteNews(article)
        newsRepository.deleteFavourite(article)
        val expected = newsRepository.getFavouriteNews().first()
        //Then
        assertEquals(expected.count(),0)
    }

    @Test
    fun getFavouriteNews() = runTest{
        //Given
        val article = Article("","",
            "","","","","","",true,false)
        //When
        newsRepository.saveFavoriteNews(article)
        val expected = newsRepository.getFavouriteNews().first()
        //Then
        assertEquals(expected.count(),1)
    }
}