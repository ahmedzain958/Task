Task
A sample android app that shows how to use ViewModels with RxJava & Koin dependency injection, in Kotlin by Clean Architecture.

These are the layers of the Clean Architecture
Presentation
Domain
Data




Communication between layers
UI calls method from ViewModel.
ViewModel executes Use case.
Use case gets data from repository.
Repository returns data from a Data Source (Remote in this case and we could use Room if we need).
Information flows back to the UI where we display the advice.


What I believe that is missing using:

- Room Database instead of static ('a penny saved is a penny earned', 
for example)
- Coroutines,
- Using resources for dimens and styles
- Code should be more documented
- Removing unused code like countdowntimer (only used to log 10 seconds to measure time taken for every api call to get the advice)
