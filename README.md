# My Widget Factory

A toy project for experimenting with workflows in the BPMN world. I use it to kick the tires of the Camunda engine - Starting with the embeddable legacy version.

The factory attempts to outline workflows where users can order widgets. 

It demonstrates a somewhat bureaucratic approval process where different personas must approve and wait for each others signatures in order for the process to finally trigger an automated provisioning of a Widget.   


## Stuff used
* SpingBoot 4.0.3
* Vaadin 25.0.5
* Camunda 7.0.24 (Legacy)

## Features
* Dynamic form-rendering based on user-tasks with fixed form-keys
* Integrates a custom UI with a BPMN suite

## Usage
`gradlew bootRun`

* http://localhost:8080 - The Widget Factory
* http://localhost:8080/camunda - Camunda cockpit (demo/demo)
