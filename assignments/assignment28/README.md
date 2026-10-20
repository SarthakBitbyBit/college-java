# assignment 28: java remote method invocation (rmi)

## title and exercise descriptions
- title: develop a java application demonstrating remote method invocation (rmi) using stub and skeleton components for remote communication.
- exercise 1: remote calculator service supporting addition, subtraction, multiplication, division, and division-by-zero exception handling via java rmi.
- exercise 2: temperature conversion service supporting bidirectional celsius-to-fahrenheit and fahrenheit-to-celsius conversions via java rmi.

## modern rmi vs legacy stubs/skeletons
in early java versions (prior to java 5), developers were required to run the `rmic` utility to manually compile static stub and skeleton classes. in modern java (including java 25), java rmi uses dynamic proxies generated at runtime by the java virtual machine (`java.lang.reflect.Proxy`). remote objects exported via `UnicastRemoteObject` communicate directly through the rmi wire protocol without requiring manually generated skeleton or stub class files.

## concepts demonstrated
- defining remote interfaces extending `java.rmi.Remote`
- declaring remote methods throwing `java.rmi.RemoteException`
- implementing remote objects extending `java.rmi.server.UnicastRemoteObject`
- programmatically creating and querying the rmi registry via `LocateRegistry`
- binding and looking up remote objects in the rmi registry
- client-side invocation of remote methods and remote exception handling

## source files
- `CalculatorRemote.java`: remote interface defining calculator operations.
- `CalculatorImpl.java`: implementation of the calculator remote interface.
- `CalculatorServer.java`: registry setup and service publisher for `CalculatorService`.
- `CalculatorClient.java`: client invoking remote arithmetic operations.
- `TemperatureRemote.java`: remote interface for temperature conversion.
- `TemperatureImpl.java`: implementation of temperature conversion remote interface.
- `TemperatureServer.java`: registry setup and publisher for `TemperatureService`.
- `TemperatureClient.java`: client invoking remote temperature conversions.

## prerequisites and dependencies
- java development kit (jdk 25 or compatible)
- open local port 1099 for the rmi registry

## compilation
```bash
javac CalculatorRemote.java CalculatorImpl.java CalculatorServer.java CalculatorClient.java TemperatureRemote.java TemperatureImpl.java TemperatureServer.java TemperatureClient.java
```

## execution commands

### 1. run exercise 1: remote calculator
start server in terminal 1:
```bash
java CalculatorServer
```
start client in terminal 2:
```bash
java CalculatorClient
```

### 2. run exercise 2: temperature conversion
start server in terminal 1:
```bash
java TemperatureServer
```
start client in terminal 2:
```bash
java TemperatureClient
```

## expected output examples

### exercise 1 (`CalculatorClient`)
```text
connecting to rmi registry at localhost:1099...
successfully obtained remote reference for CalculatorService
performing remote calculations with a = 45.0, b = 15.0
remote add(45.0, 15.0) = 60.0
remote subtract(45.0, 15.0) = 30.0
remote multiply(45.0, 15.0) = 675.0
remote divide(45.0, 15.0) = 3.0
testing remote division by zero handling...
caught expected exception: division by zero is not permitted
all remote calculator operations completed successfully
```

### exercise 2 (`TemperatureClient`)
```text
connecting to rmi registry at localhost:1099...
successfully obtained remote reference for TemperatureService
celsius to fahrenheit: 100.00 C = 212.00 F
fahrenheit to celsius: 98.60 F = 37.00 C
freezing point check: 0.00 C = 32.00 F
remote temperature conversion test completed successfully
```

## screenshots
- calculator client: `screenshots/calculator_client.png`
- temperature client: `screenshots/temperature_client.png`

## conclusion
java remote method invocation allows seamless distributed computing where client applications invoke methods on remote server objects as though they were local. both services demonstrated registry binding, lookup, exception transmission, and modern dynamic proxy handling.
