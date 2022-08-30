package com.sixtassignment.base

import com.sixtassignment.AssignemntApplication
import com.sixtassignment.BuildConfig
import com.sixtassignment.di.configureTestAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler


import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log



import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner



@RunWith(PowerMockRunner::class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(
    Log::class,
    Handler::class,
    Looper::class,
    TextUtils::class,
    BuildConfig::class,
    AssignemntApplication::class
)
abstract class BaseUnitTest : AutoCloseKoinTest() {

    open lateinit var context: Context
    lateinit var resources: Resources
    lateinit var sharedPreferences: SharedPreferences
    lateinit var mockWebServer: MockWebServer

    lateinit var testScheduler: TestCoroutineScheduler


    @Mock
    lateinit var application: AssignemntApplication

    @Mock
    lateinit var mockContext: Context

    @Mock
    var assetManager: AssetManager? = null

    /**
     * Set Mockwebserver url
     */
    fun getMockWebServerUrl() = mockWebServer.url("/").toString()

    @Before
    @Throws(Exception::class)
    open fun setUp() {

        // Initializes the mock environment
        MockitoAnnotations.initMocks(this)

        // Initializes the mock webserver
        mockWebServer = MockWebServer()


        startMockWebserver()

        // Mocks the generic android dependencies such as Context, Looper, etc.
        mockAndroidDependencies()

        // Mocks android logs
        mockLogs()




        // Sets the RXJava schedulers for unit tests
     //   Co.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
       // RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        stopKoin()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @After
    open fun tearDown() {
        stopMockWebserver()
    }

    /**
     * Method which starts the mockwebserver
     */
    private fun startMockWebserver() {
        mockWebServer.start(8081)
    }

    /**
     * Method which stops the mock webserver
     */
    private fun stopMockWebserver() {
        mockWebServer.shutdown()
    }

    /**
     * This function will mock all the android Log related dependencies
     */
    private fun mockLogs() {
        PowerMockito.mockStatic(Log::class.java)
        val logAnswer = Answer<Void> { invocation ->
            val tag = invocation.arguments[0] as String
            val msg = invocation.arguments[1] as String
            println(invocation.method.name.toUpperCase() + "/[" + tag + "] " + msg)
            null
        }
        PowerMockito.doAnswer(logAnswer).`when`(
            Log::class.java, "d",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(
            Log::class.java, "i",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(
            Log::class.java, "w",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(
            Log::class.java, "e",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(
            Log::class.java, "wtf",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )

        PowerMockito.doAnswer { invocation ->
            val s = invocation.arguments[0] as String
            s.isEmpty()
        }.`when`(TextUtils::class.java, "isEmpty", ArgumentMatchers.anyString())
    }

    /**
     * This function will mock all the SharedPreference related dependencies
     */

    /**
     * This method initializes the retrofit module
     */


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun mockAndroidDependencies() {
        context = PowerMockito.mock(Context::class.java)
        resources = PowerMockito.mock(Resources::class.java)
        sharedPreferences = PowerMockito.mock(SharedPreferences::class.java)
        testScheduler = TestCoroutineScheduler()

        PowerMockito.mockStatic(Looper::class.java)
        PowerMockito.mockStatic(Handler::class.java)
        PowerMockito.mockStatic(TextUtils::class.java)
        PowerMockito.mockStatic(AssignemntApplication::class.java)
        PowerMockito.`when`(Looper.getMainLooper()).thenReturn(null)
        PowerMockito.`when`(context.resources).thenReturn(resources)
        AssignemntApplication.setInstance(application)
        PowerMockito.`when`(application.applicationContext).thenReturn(mockContext)
        PowerMockito.`when`(AssignemntApplication.applicationContext()).thenReturn(context)
        PowerMockito.`when`(AssignemntApplication.applicationContext().assets).thenReturn(assetManager)

        PowerMockito.whenNew(Handler::class.java).withAnyArguments().thenReturn(null)
    }
}
