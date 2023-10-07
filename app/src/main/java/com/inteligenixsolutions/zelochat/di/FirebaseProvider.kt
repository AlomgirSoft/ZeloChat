package com.inteligenixsolutions.zelochat.di

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseProvider {
    @Singleton
    @Provides
    fun providerAuth():FirebaseAuth{

        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideDb(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }


    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()


    @Provides
    @Singleton
    fun provideStorageReference(storage: FirebaseStorage, auth: FirebaseAuth): StorageReference =
        storage.getReference("Upload").child("image${auth.currentUser?.uid}")

    @Provides
    @Singleton
    fun provideDatabaseReference(database: FirebaseDatabase): DatabaseReference {
        return database.getReference("user")
    }



    @Provides
    @Singleton
    fun provideUser(): FirebaseUser {
        return FirebaseAuth.getInstance().currentUser!!


    }

//
//    @Provides
//    @Singleton
//    fun provideDatabaseReference(): DatabaseReference {
//        // Provide an instance of DatabaseReference here
//        return FirebaseDatabase.getInstance().reference
//    }

}