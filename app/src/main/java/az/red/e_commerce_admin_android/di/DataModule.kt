package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.data.remote.*
import az.red.e_commerce_admin_android.data.remote.user.*
import az.red.e_commerce_admin_android.data.remote.auth.*
import az.red.e_commerce_admin_android.data.remote.brand.BrandRepository
import az.red.e_commerce_admin_android.data.remote.brand.BrandRepositoryImpl
import az.red.e_commerce_admin_android.data.remote.brand.BrandService
import az.red.e_commerce_admin_android.data.remote.category.CategoryRepository
import az.red.e_commerce_admin_android.data.remote.category.CategoryRepositoryImpl
import az.red.e_commerce_admin_android.data.remote.category.CategoryService
import az.red.e_commerce_admin_android.data.remote.order.OrderRepository
import az.red.e_commerce_admin_android.data.remote.order.OrderRepositoryImpl
import az.red.e_commerce_admin_android.data.remote.order.OrderService
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.ProductRepositoryImpl
import az.red.e_commerce_admin_android.data.remote.product.ProductService
import az.red.e_commerce_admin_android.utils.SessionManager
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        val interceptor = HeaderInterceptor(get())
        val client = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            val logger =
                HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logger)
        }
        client.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(getProperty("base_url") as String)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        SessionManager(androidContext())
    }
    single {
        Gson()
    }



    /////////////////////////////////////////////////////////// Auth ///////////////////////////////////////////////////////////
    factory<AuthService> { get<Retrofit>().create(AuthService::class.java) }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }

    /////////////////////////////////////////////////////////// User ///////////////////////////////////////////////////////////
    factory<UserService> { get<Retrofit>().create(UserService::class.java) }
    factory<UserRepository> { UserRepositoryImpl(get()) }


    /////////////////////////////////////////////////////////// Product ///////////////////////////////////////////////////////////
    factory<ProductService> { get<Retrofit>().create(ProductService::class.java) }
    factory<ProductRepository> { ProductRepositoryImpl(get()) }

    /////////////////////////////////////////////////////////// Brand ///////////////////////////////////////////////////////////
    factory<BrandService> { get<Retrofit>().create(BrandService::class.java) }
    factory<BrandRepository> { BrandRepositoryImpl(get()) }

    /////////////////////////////////////////////////////////// Category ///////////////////////////////////////////////////////////
    factory<CategoryService> { get<Retrofit>().create(CategoryService::class.java) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get()) }
    
    /////////////////////////////////////////////////////////// Order ///////////////////////////////////////////////////////////
    factory<OrderService> { get<Retrofit>().create(OrderService::class.java) }
    factory<OrderRepository> { OrderRepositoryImpl(get()) }

}