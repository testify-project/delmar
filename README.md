# Testify
[![Build Status](https://travis-ci.org/testify-project/delmar.svg?branch=develop)](https://travis-ci.org/testify-project/delmar)
[![CodecovIO](https://codecov.io/github/testify-project/delmar/coverage.svg?branch=develop)](https://codecov.io/github/testify-project/delmar?branch=develop)
[![Maven Central](https://img.shields.io/maven-central/v/org.testifyproject/parent.svg)]()
[![Javadocs](http://www.javadoc.io/badge/org.testifyproject/api.svg?color=red)](http://www.javadoc.io/doc/org.testifyproject/api)
[![License](https://img.shields.io/badge/license-Apache%20License%202-lightgrey.svg)](https://github.com/testify-project/delmar/blob/develop/LICENSE)
[![Stories in Progress](https://badge.waffle.io/testify-project/delmar.svg?label=In%20Progress&title=In%20Progress)](http://waffle.io/testify-project/delmar)
[![Stories in Ready](https://badge.waffle.io/testify-project/delmar.svg?label=ready&title=Ready)](http://waffle.io/testify-project/delmar)
[![Join the chat on Gitter](https://badges.gitter.im/testify-project/Lobby.svg)](https://gitter.im/testify-project/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Throughput Graph](https://graphs.waffle.io/testify-project/delmar/throughput.svg)](https://waffle.io/testify-project/delmar/metrics)

## Overview
Delmar is a distributed workspace replication and test execution framework. It allows development teams to continously share their development workspaces and compute resources with teammates in order to execute test cases faster.


## High Level Goals
1. Discover workstations on the network
- A workstation should advertise its address and port information
- A workstation should be able to discover other workstations
1. Replicate and synchronize workstation workspaces
- A workstation should be able to connect to discovered workstations and replicate their workspace
- A workstation should be able to detect changes to its workspace and alert other workstations of the changes
- A workstation should be able to detect changes to other workstation workspaces and synchronize those change with its copy of their workspace
1. Distribute the execution of test cases among workstations
- A workstation should be able to submit a list of tests it wants to execute in a distributed manner
- A workstations should be able to pickup tests to execute from a quite and execute the tests
- A workstation should be able to publish the result of the test execution
- A workstation should be able to read published test execution result

## Features
TODO

## Learning
TODO

## Issue Tracking
Report issues via the [Github Issues][github-issues]. Think you've found a bug?
Please consider submitting a reproduction project via the a new [Github Issue][github-issues-new].

## Issue Pull Request
[Pull requests](http://help.github.com/send-pull-requests) are welcome.

## Staying in Touch
Hit us up on [Gitter][gitter].

## License
Delmar is released under [Apache Software License, Version 2.0](LICENSE).

Enjoy and keep on Testifying!


[docs]: http://fitburio.github.io/delmar
[github-issues]: https://github.com/testify-project/delmar/issues
[github-issues-new]: https://github.com/testify-project/delmar/issues/new
[gitter]: https://gitter.im/testify-project/Lobby
[java-for-small-team]: https://www.gitbook.com/book/ncrcoe/java-for-small-teams/details

