<!-- 
This README template got from https://github.com/othneildrew/Best-README-Template/tree/master
Thx a lot Othneil Drew for this awesome template, shared with MIT License!
-->

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
<!-- [![Contributors][contributors-shield]][contributors-url] pro subscribe gitHub required-->
<!-- [![Forks][forks-shield]][forks-url] -->
<!-- [![Stargazers][stars-shield]][stars-url] -->
<!-- [![Issues][issues-shield]][issues-url] -->
<!-- [![MIT License][license-shield]][license-url] -->
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/EvgenySamarin/FinanceStocks">
    <img src="https://img.icons8.com/3d-fluency/94/money-box.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">FinanceStocks</h3>

  <p align="center">
    A simple android application for tracking your stocks and crypto assets.
    <br />
    <a href="https://github.com/EvgenySamarin/FinanceStocks"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/EvgenySamarin/FinanceStocks">View Demo</a>
    ·
    <a href="https://github.com/EvgenySamarin/FinanceStocks/issues">Report Bug</a>
    ·
    <a href="https://github.com/EvgenySamarin/FinanceStocks/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a sample project to grub financial data from Yahoo Finance API and display it in a simple
android application.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![RapidApi][Yahoo-Finance-API from RapidApi]][https://rapidapi.com/sparior/api/yahoo-finance15]
* kotlin = "1.9.0"
* coroutines = "1.7.3"
* coreKtx = "1.12.0"
* gson = "2.10.1"
* retrofit = "2.9.0"
* okHttpLoggingInterceptor = "4.11.0"
* hilt = "2.48"
* hiltComposeNavigation = "1.1.0"
* viewMaterial = "1.10.0"
* fragmentKtx = "1.6.2"
* composeActivity = "1.8.0"
* composeLifecycleRuntime = "2.6.2"
* composeNavigation = "2.7.5"
* bomCompose = "2023.08.00"

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] It must have a list and description screens.
- [x] Use Yahoo Finance API (https://rapidapi.com/apidojo/api/yh-finance) for the data list
  (market/v2/get-summary) and present it. Every 8 seconds, this screen should update the
  list from the API. Yahoo Finance has limits for free registration. Because of that, it might
  be required to register a second time. - this link is broken, because API no loner supported,
  so I used another one: https://rapidapi.com/sparior/api/yahoo-finance15
  (methods most_actives and asset-profile)
- [x] On the top of the list screen, the search bar should let the user filter out stocks by the
  title.
- [x] When pressed on the list item, it should get detailed info (stock/v2/get-summary) and
  present it.
- [x] Use simple native UI elements (no graphs needed). However, try presenting the
  information that reflects stocks the most. If possible use Jetpack Compose in the main
  page and XML Layout in the details screen.
- [ ] Create Unit tests with reasonable code coverage. - still in progress, only for one useCase
- [x] Use Coroutines, MVVM.
- [x] Do not use any additional frameworks, except those related to the Coroutines,
  networking, or for the DI.

See the [open issues](https://github.com/EvgenySamarin/FinanceStocks/issues) for a full list of
proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Twitter (X) / [@EvgenySamarin](https://twitter.com/EvgenySamarin) - ey.samarin@gmail.com

Project
Link: [https://github.com/EvgenySamarin/FinanceStocks](https://github.com/EvgenySamarin/FinanceStocks)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/EvgenySamarin/FinanceStocks.svg?style=for-the-badge

[contributors-url]: https://github.com/EvgenySamarin/FinanceStocks/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/EvgenySamarin/FinanceStocks.svg?style=for-the-badge

[forks-url]: https://github.com/EvgenySamarin/FinanceStocks/network/members

[stars-shield]: https://img.shields.io/github/stars/EvgenySamarin/FinanceStocks.svg?style=for-the-badge

[stars-url]: https://github.com/EvgenySamarin/FinanceStocks/stargazers

[issues-shield]: https://img.shields.io/github/issues/EvgenySamarin/FinanceStocks.svg?style=for-the-badge

[issues-url]: https://github.com/EvgenySamarin/FinanceStocks/issues

[license-shield]: https://img.shields.io/github/license/EvgenySamarin/FinanceStocks.svg?style=for-the-badge

[license-url]: https://github.com/EvgenySamarin/FinanceStocks/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/evgenysamarin

[product-screenshot]: images/screenshot.png

[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
