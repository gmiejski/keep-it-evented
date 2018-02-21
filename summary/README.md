# notes for summary

1. At first I didn't understand how to implement repository to use command and event handler. But of course it makes sense - the fact that we are relying on event sourcing should be hidden. This way repository can later be changed with some completely different idea.
2. When implementing branch |KIE-4-tasks-notes| where I decided to introduce second aggregate for list notes, I have created another pair of event handler, command handler, repository. Then I have noticed, that both eventHandler and repository has exactly the same implementation. Some generics and voila! Removed code deduplication - now only CommandHandler must be created for each new Aggregate I will ever need!

