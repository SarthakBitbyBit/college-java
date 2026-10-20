# assignment 25: thread class and thread lifecycle

## title and exercise descriptions
- title: write a java program using the thread class to create multiple threads and demonstrate their lifecycle.
- exercise 1: create a program where multiple threads print numbers simultaneously, demonstrating concurrent execution, thread naming, and thread lifecycle states (new, runnable, terminated).
- exercise 2: create a multithreaded bank transaction simulation using the thread class where deposit and withdrawal threads access a shared bank account with synchronization and balance validation.

## concepts demonstrated
- extending `java.lang.Thread`
- overriding `run()`
- thread lifecycle states (`NEW`, `RUNNABLE`, `TERMINATED`) observed via `getState()`
- concurrent execution with `start()`, `sleep()`, and `join()`
- shared resource access synchronization using `synchronized` methods
- race condition prevention and safe transaction handling

## source files
- `ThreadNumbersDemo.java`: concurrent number printer demonstrating thread lifecycle.
- `BankTransactionThreads.java`: multithreaded bank deposit and withdrawal simulation.

## prerequisites and dependencies
- java development kit (jdk 25 or compatible)
- standard java runtime library (no external jars required)

## compilation
```bash
javac ThreadNumbersDemo.java BankTransactionThreads.java
```

## execution commands
### 1. run exercise 1: thread lifecycle and number printing
```bash
java ThreadNumbersDemo
```

### 2. run exercise 2: synchronized bank transactions
```bash
java BankTransactionThreads
```

## expected output examples
### exercise 1 (`ThreadNumbersDemo`)
```text
demonstrating thread lifecycle with thread class
thread-1 state before start: NEW
thread-2 state before start: NEW
thread-3 state before start: NEW
thread-1 state after start: RUNNABLE
thread-2 state after start: RUNNABLE
thread-3 state after start: RUNNABLE
thread-3 printed: 11
thread-1 printed: 1
thread-2 printed: 6
...
thread-1 state after completion: TERMINATED
thread-2 state after completion: TERMINATED
thread-3 state after completion: TERMINATED
all number printer threads have completed
```

### exercise 2 (`BankTransactionThreads`)
```text
starting multithreaded bank transaction simulation
initial account balance: 1000.0
deposit-thread depositing: 500.0
deposit-thread deposit completed. current balance: 1500.0
withdraw-thread attempting to withdraw: 400.0
withdraw-thread withdrawal successful. current balance: 1100.0
...
final verified account balance: 300.0
```

## screenshots
- exercise 1 output: `screenshots/exercise1_output.png`
- exercise 2 output: `screenshots/exercise2_output.png`

## conclusion
the thread class allows creation and management of independent threads of execution in java. thread lifecycle transitions from new to runnable and terminated were verified. thread synchronization effectively prevented race conditions on the shared bank account balance.
