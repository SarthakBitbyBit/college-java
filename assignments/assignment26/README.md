# assignment 26: runnable interface

## title and exercise descriptions
- title: create and execute multiple threads using the runnable interface.
- exercise 1: use the runnable interface to implement two independent tasks and run them concurrently using separate thread objects.
- exercise 2: implement concurrent bank deposit and withdrawal operations using the runnable interface with synchronized balance updates and insufficient funds validation.

## concepts demonstrated
- implementing `java.lang.Runnable`
- separating task definition from thread execution
- passing runnable instances to `Thread` constructors
- concurrent task coordination with `join()`
- synchronization on a shared bank account resource
- overdraft protection and state consistency

## source files
- `RunnableTasksDemo.java`: concurrent tasks demonstrating the runnable interface.
- `RunnableBankTransactions.java`: deposit and withdrawal tasks executing concurrently on a shared bank account.

## prerequisites and dependencies
- java development kit (jdk 25 or compatible)
- standard java runtime library

## compilation
```bash
javac RunnableTasksDemo.java RunnableBankTransactions.java
```

## execution commands
### 1. run exercise 1: concurrent runnable tasks
```bash
java RunnableTasksDemo
```

### 2. run exercise 2: runnable bank transactions
```bash
java RunnableBankTransactions
```

## expected output examples
### exercise 1 (`RunnableTasksDemo`)
```text
demonstrating concurrent tasks using runnable interface
task-b computing batch: 1
task-a processing item: 1
task-a processing item: 2
task-b computing batch: 2
...
task-a completed all items
task-b completed all batches
both runnable tasks have finished execution successfully
```

### exercise 2 (`RunnableBankTransactions`)
```text
starting runnable bank transactions simulation
initial account balance: 2000.0
deposit-worker depositing: 400.0
deposit-worker deposit completed. current balance: 2400.0
withdraw-worker requesting withdrawal: 800.0
withdraw-worker withdrawal approved. current balance: 1600.0
...
withdraw-worker requesting withdrawal: 500.0
withdraw-worker withdrawal rejected: insufficient balance (available: 300.0)
final verified balance: 300.0
```

## screenshots
- exercise 1 output: `screenshots/exercise1_output.png`
- exercise 2 output: `screenshots/exercise2_output.png`

## conclusion
the runnable interface provides a flexible approach to multithreading by separating task execution logic from the thread object. both independent tasks and synchronized shared resource operations completed reliably under concurrent execution.
