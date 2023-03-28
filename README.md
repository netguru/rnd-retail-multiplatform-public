<div align="center">
  <img alt="R&D Retail" src="https://user-images.githubusercontent.com/43965151/188373500-d193700f-706c-4957-92be-ec0964bcd463.png"/>
  <h1>Retail - KMM application for iOS and Android</h1>
</div>

<div align="center">
App demonstrating KMM solutions in an eCommerce furniture setting, with shared resources, logic and data.

  <br/><em>Brought with</em> &nbsp;❤️ <em>by</em> &nbsp; <a href="https://www.netguru.com"><img align="center" alt="Netguru logo" src="https://user-images.githubusercontent.com/43965151/188132449-b5774acd-4a88-4156-b82d-553a1993811e.png" width='30'/></a>
  
  Read more about the 2-year reserach on our blog: <a href="https://www.netguru.com/blog/developing-ecommerce-app-kotlin-multiplatform">Developing an Ecommerce App in Kotlin Multiplatform: A Use Case</a>
  
  <img alt="Promo1" src="https://user-images.githubusercontent.com/43965151/188124996-f359edd6-33af-4a79-a74e-a5f7499014b4.png" height='549' width='260'/>
  <img alt="Promo2" src="https://user-images.githubusercontent.com/43965151/188125059-89eec80e-775d-42e4-aab1-b974e4e9ef61.png" height='549' width='260'/>
  <img alt="Promo3" src="https://user-images.githubusercontent.com/43965151/188125102-43a4af30-274f-4bff-8f5e-490d1ca0df35.png" height='549' width='260'/>
</div>

## Code distribution
Charts presents lines of code for each platform and common modules. It was counted using [Statistic AS plugin](https://plugins.jetbrains.com/plugin/4509-statistic).
What is **not** included in measurement:
- Test and build folders - for instance iOS `Pods`.
- Comments and blank lines.

![Retil code distribution](https://user-images.githubusercontent.com/12357195/188078891-12c3aa2a-11f6-486d-829e-bed0cece151c.jpg)

## Running/Development
*Repository cloning time might be extended because of the 3D models used in the application, which are stored locally.*
### Android:
Open the root folder in Android Studio and a proper configuration should be detected.  You should be able to run the project in a selected emulator.

### iOS:
If you haven't yet, install CocoaPods:
```sh  
sudo gem install cocoapods
```  
Then, inside directory "iosApp", run:
```sh  
pod install
```  
Then in XCode open the "iosApp" folder and a proper configuration should be detected.  You should be able to run the project in a selected emulator.

## Running Tests
### Android:
   In Android Studio in the project view right click on "androidApp" module and select Run 'All Tests'
### iOS:
   In XCode from the app menu select Product > Test
### Common:
   In Android Studio in the project view, open a specific common module, open commonTest directory,
   right click on a specific test file and select "Run `*filename*`"

## Common code

### Modules

Project is composed of the following modules:

1. **common** - both Android and iOS apps always access common part via common module. This module is an umbrella module which aggregates all other modules, initializes a dependency graph and exposes a single factory called `Provider`.

2. **commonData** - common data is one of so-called plugin modules. This type of module utilise a Dependency Inversion Principle by providing an implementation for some interfaces exposed by the domain module.

3. **commonResources** - stores all shared
images, fonts, colors and strings. Those resources are exposed with [Moko](https://github.com/icerockdev/moko-resources). Because of Moko limitations, it is currently not under common module.

4. **commonDomain** - domain is the brain of our application. It handles the application state and performs business logic operations on it. To provide a better scalability we divide implementation of the domain into 4 main building blocks: States, Stores, Services, and Queries.

5. **buildSrc** - stores Kotlin dependencies used both on Android and iOS modules.

6. **androidApp** - produces executable Android application

7. **iosApp** - produces executable iOS application

### Resources
The project uses shared resources aggregated in ```commonResources``` module.
For more information about sharing resources, head to [moko-resources](https://github.com/icerockdev/moko-resources) library.

#### Strings
To add a new multiplatform string head to ```commonResources/src/commonMain/resources/MR/base/strings.xml```

To use the string in an Android composable, use ```dev.icerock.moko.resources.compose.stringResource```, example:
```Text(text = stringResource(MR.strings.common_skip))```

To use the string in SwiftUI:
```LocalizedStringKey(MR.strings().common_skip.resourceId)```

#### Fonts
To add a new font head to ```commonResources/src/commonMain/resources/MR/fonts```

New fonts should follow the existing ```Name-Type``` naming convention.

## Architecture

Below you can find a full diagram which presents a high lever structure of the KMM Architecture.

![image](https://user-images.githubusercontent.com/44615328/188136993-69f7abaa-25e3-4f1d-b4cd-11f22808917d.png)

### Store - a state holder
It is a simple unit which has only one responsibility - keeping an application state. Store should have the simplest api possible, like `getState` and `setState`.

Our domain can be split into many stores. It is a good practice to make each store be responsible for only one type of data, like `SettingsStore`, `AuthStore`, `ProductsStore`. This way we can follow a Single Responsibility Principle and improve readability of our code.

### Service - writing business logic
This unit is responsible for performing handling user intents and updating the state accordingly. We call it a **writing business logic**.

We can have multiple services responsible for different parts of our business logic. Services can use stores to read current application state and update it.

Services also have access to other units like data sources or system APIs (Navigation, Preferences etc) 

### Query - reading business logic
Service is a one half of Unidirectional Data Flow. It handles user intentions and performs state updates based on them. Query is second half of the UDF cycle. It helps us define a **reading business logic** of our application.

It allows us to filter or transform the domain data to prepare it for displaying to the user. Thanks to the Query we can keep a single source of truth in the Store and transform it into multiple **dervied states**.

# Contributing

[Contributing guidelines](CONTRIBUTING.md)

# License

This library is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
