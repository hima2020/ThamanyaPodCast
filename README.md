# Thmanyah Android App

<p align="center">
  <img alt="Android" src="https://img.shields.io/badge/Android-Modern%20App-3DDC84?logo=android&logoColor=white">
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.x-7F52FF?logo=kotlin&logoColor=white">
  <img alt="Jetpack Compose" src="https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white">
  <img alt="Hilt" src="https://img.shields.io/badge/Hilt-DI-34A853?logo=google&logoColor=white">
  <img alt="Retrofit" src="https://img.shields.io/badge/Retrofit-Networking-0F9D58">
  <img alt="License" src="https://img.shields.io/badge/License-MIT-yellow">
</p>

A modern Android application built with **Jetpack Compose**, showcasing a **clean, modular architecture**, robust state management, and dynamic UI layouts for podcast-like content.

---

## Preview

<p align="center">
  <table>
    <tr>
      <th>Home</th>
      <th>Search</th>
    </tr>
    <tr>
      <td><img src="docs/screenshots/home.png" width="300" alt="Home Screen"></td>
      <td><img src="docs/screenshots/search.png" width="300" alt="Search Screen"></td>
    </tr>
  </table>
</p>

---

## Features

- **Dynamic Content Display** — Podcasts, Episodes, Audiobooks, Audio Articles.
- **Modular Architecture** — Clear separation across `app`, `domain`, and `data`.
- **Debounced Search** — Efficient querying without spamming the network.
- **Adaptive Section Layouts** — Row, square items, big squares, two-line horizontal scroll.
- **Graceful Loading & Error States** — Predictable user feedback & retry flows.
- **Polymorphic JSON** — Custom Gson adapter to parse mixed item types safely.

---

## Tech Stack

- **Kotlin**, **Jetpack Compose**, **Material 3**
- **Hilt** (Dependency Injection)
- **Coroutines** & **Flow**
- **Retrofit** + **OkHttp**
- **Gson** (with custom type adapters)

---

## Architecture

Follows **Clean Architecture** with MVVM-style presentation.

```mermaid
flowchart LR
  UI[Compose UI] --> VM[ViewModel]
  VM --> Repo[Repository (Interface)]
  Repo -->|Network| API[Retrofit + Gson]