# The Widget Factory

A toy project for experimenting with workflows in the BPMN world. I use it to kick the tires of the Operaton engine.

The factory attempts to outline workflows where users can order widgets. 

It demonstrates a somewhat bureaucratic approval process where different personas must approve and wait for each others signatures in order for the process to finally trigger an automated provisioning of a Widget.   


## Stuff used
* SpingBoot 4.0.5
* Vaadin 25.0.5
* Operaton 2.0.0

## Features
* Dynamic form-rendering based on user-tasks with fixed form-keys
* Integrates a custom UI with a BPMN suite

## Usage
`gradlew bootRun`

* http://localhost:8080 - The Widget Factory
* http://localhost:8080/operaton - Operaton cockpit (demo/demo)
