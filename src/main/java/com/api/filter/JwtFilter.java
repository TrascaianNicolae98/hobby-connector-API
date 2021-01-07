package com.api.filter;

/*@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired private AppUserService userService;

  @Override
  protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException, AppException {
    final String jwt = request.getHeader("authorization");
    Long userId = null;
    String path = request.getRequestURI();
    if (path.equals("/api/loginWithGoogle")
        || path.equals("/api/loginWithAccount")
        || path.equals("/api/signUp")
        || path.equals("/api/confirm-account")) {
      filterChain.doFilter(request, response);
    } else {
      try {
        this.jwtUtil.isJwtNull(jwt);
        userId = this.jwtUtil.extractId(this.jwtUtil.extractAllClaims(jwt));
        AppUser appUser = this.userService.findById(userId);
        this.jwtUtil.validateToken(jwt, appUser);
        request.setAttribute("claims", this.jwtUtil.extractAllClaims(jwt));
        filterChain.doFilter(request, response);
      } catch (AppException e) {
        response.sendError(e.getStatusCode().value(), e.getMessage());
      }
    }
  }
}*/
