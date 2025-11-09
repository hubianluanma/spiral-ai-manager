# Spiral AI Manager

## 项目概述

Spiral AI Manager 是一个基于 Spring Boot、Spring Security 和 JPA 技术栈的服务端程序，旨在提供一个灵活且安全的平台，用于管理和对接多种 AI 服务。目前支持 Ollama、DeepSeek 和 OpenAI 等主流 AI 提供商，并且设计上允许根据需求轻松扩展以支持更多的 AI 服务。

## 技术栈

- **后端框架**: Spring Boot
- **数据库**: PostgreSQL
- **持久层**: JPA (Java Persistence API)
- **安全认证**: Spring Security（基于角色的权限控制）

## 功能特性

- **多 AI 提供商支持**: 已经集成了对 Ollama、DeepSeek 和 OpenAI 的支持。
- **可扩展性**: 通过定义统一的 `AIProvider` 接口，可以方便地添加新的 AI 服务提供商。
- **安全性**: 利用 Spring Security 实现了强大的认证和授权机制，确保只有授权用户才能访问特定资源或执行特定操作。
- **数据持久化**: 使用 JPA 进行对象关系映射(ORM)，简化了与 PostgreSQL 数据库之间的交互。

## 项目状态

请注意，此项目仍在积极开发中。我们正在努力完善各项功能，并解决已知问题。如果您有任何建议或者发现了任何问题，请随时联系我们！

## 如何贡献

欢迎任何形式的贡献！无论是提交 bug 报告、提出新功能请求还是直接参与代码开发，我们都非常感激您的帮助。请参阅 [CONTRIBUTING.md](CONTRIBUTING.md) 文件获取更多关于如何贡献的信息。

## 联系方式

对于任何疑问或反馈，请发送邮件至 huhailong1121@gmail.com 或者 huhailong1121@icloud.com。

---

这份 README 文件提供了关于 Spiral AI Manager 项目的初步介绍。随着项目的进一步发展和完善，我们将持续更新文档内容。感谢您对本项目的关注和支持！
