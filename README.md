# ISKCON-Preaching-Assistant

## Overview

Here I shall define the entities accessible through the API. You can access the API Specifications for any of the entities by clicking on their respective names.

1. [**Devotee**](https://bitbucket.org/hariksetra/iskcon-preaching-assistant/wiki/Devotee): The `Devotee` entity represents a devotee. It holds the particulars of the devotee like name, area, phone, etc. It also provides links to other entities like `UserAccount`, `Program`, `ProgramAssignment`, etc.

2. **[UserAccount](https://bitbucket.org/hariksetra/iskcon-preaching-assistant/wiki/UserAccount)**: The `UserAccount` entity is used to hold the user credentials and the authority for a particular user so that s/he may login to the application to access functionality confirming to their authority.

3. **[Program](https://bitbucket.org/hariksetra/iskcon-preaching-assistant/wiki/Program)**: Represents any program taking place in association with the temple. It holds the name of the program and the mentor(`Devotee`).

4. **[ProgramAssignment](https://bitbucket.org/hariksetra/iskcon-preaching-assistant/wiki/ProgramAssignment)**: Represents the current status of a `Devotee` by specifying which program he is currently attending. This entity is not responsible for holding the history. If a `Devotee` is reassigned, the `ProgramAssignment` needs to be updated.

5. **[FollowUp](https://bitbucket.org/hariksetra/iskcon-preaching-assistant/wiki/FollowUp)**: Represents the history of a follow-up and the response  a volunteer(`Devotee`) got from an attendee(`Devotee`).

6. **[FollowUpAssignment](https://bitbucket.org/hariksetra/iskcon-preaching-assistant/wiki/FollowUpAssignment)**: Represents the assignment of volunteers(`Devotee`) to attendees(`Devotee`) for a particular program(`Program`).
