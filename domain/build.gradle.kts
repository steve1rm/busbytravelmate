plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

ksp {
    /* Compile time checking, similar to dagger and hilt */
    arg("KOIN_CONFIG_CHECK", "true")
 //   arg("KOIN_DEFAULT_MODULE", "false")
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
}