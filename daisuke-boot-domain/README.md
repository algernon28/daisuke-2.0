# Daisuke 2.0
## Domain Module
This module contains the datamodel for the application, and the adapter interfaces.

### Package domain.adapters
The assumption is that the domain is represented by these entities:
* Component: this is the core entity, a component can be a class, a code snippet, a project, or anything that can be analysed, depending on the platform.
* Issue: this is the class of the problem detected on the component. Typically identified by a category like "BUG" or "CODE SMELL", and a severity like "BLOCKER" or "CRITICAL". The specific category and severity classification depends on the target platform.
* Rule: a rule represents a condition which triggers an Issue.

### Package domain.model
This package contains the DTO (Data Transfer Objects) which Daisuke is built on. The model is generically inspired by SonarQube, but it is intended to be generic, no matter if the target is a static analysis platform, or a generic issue management.
