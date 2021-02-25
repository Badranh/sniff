# Sniff (Under Development)
## _A Fast Kotlin JVM Image Loader/Cacher_

![N|Kotlin](https://camo.githubusercontent.com/96c091300467cf1fd3aa74f9d83beb359626ed3b169a3a13ed62d1d91ada16c9/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6b6f746c696e2d312e342e32302d626c75652e7376673f6c6f676f3d6b6f746c696e)

Sniff is a simple image loader for Kotlin JVM and it supports the new jetpack compose ui for desktop 🔥 , inspired from Picasso for android and built on the top of Ktor Client as default. It will support different kinds of caching, the library it self will be easily extendible and re-usable.

## Currently Working On
- Singleton instance of Sniff from factory.
- Optimize in-memory caching.
- Create Disk caching.
- Create factories for each service.
- Add fade option upon rendering.
- Enhance fallbacks and retries of network calls, apply them in the correct way.
- Project modularization.
- Option to apply image color filters in an easy way
- Option to add place holder when loading

## Project Structure

- -> cache: All caching strategies with a single access point
- -> component: Jetpack ui image view component
- -> loader: All types of loaders Online,Offline and both
- -> service: contains HTTPClients to dowload the image (currently KTOR)
- -> sniff: will be the public access point for library users.

## How To use

``` NetworkImage(url = URL,scope = coroutineScope) ```

## The project depends on

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | VERSION |
| ------ | ------ |
| KTOR | [1.5.1]  |
## Development

Want to contribute? Great!

Fork and submit a pull request, you can start with one of the 
##### Currently Working On Section
## License

MIT

**Free Software, Hell Yeah!**


