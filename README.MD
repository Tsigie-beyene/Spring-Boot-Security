
---

##  Spring Security Framework

### What is Spring Security?

Spring Security is a **powerful and flexible security framework** for Java applications that provides:

- Authentication
- Authorization
- Protection against common attacks
- Integration with OAuth2, OpenID Connect, LDAP, JWT, and more

It is built around **filters, security contexts, and pluggable authentication mechanisms**.

---

## Spring Security Core Architecture

Key internal components:
- `SecurityFilterChain`
- `DelegatingFilterProxy`
- `AuthenticationManager`
- `AuthenticationProvider`
- `UserDetailsService`
- `SecurityContextHolder`

Every HTTP request passes through a **chain of security filters** before reaching application controllers.

---

## Authentication vs Authorization

### Authentication
> Verifies **who the user is**

Examples:
- Username & password login
- JWT validation
- OAuth2 login

### Authorization
> Determines **what the user is allowed to do**

Examples:
- Role-based access (`ROLE_ADMIN`)
- Authority-based access (`READ_ACCOUNT`)

| Aspect | Authentication | Authorization |
|------|---------------|---------------|
| Purpose | Identity verification | Access control |
| Happens | At login | After authentication |
| Spring API | AuthenticationManager | AccessDecisionManager |

---

## Password Management in Spring Security

Spring Security **never stores plain text passwords**.

### PasswordEncoders

Recommended encoders:
- `BCryptPasswordEncoder` ✅ (Best practice)
- `SCryptPasswordEncoder`
- `Argon2PasswordEncoder`

Example:
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
