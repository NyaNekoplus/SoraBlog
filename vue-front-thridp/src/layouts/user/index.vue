<template>
  <div class="login js login-action-login wp-core-ui  locale-zh-cn">
    <div id="bg"
         style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; z-index: -1; overflow: hidden;"><img
        src="https://cdn.jsdelivr.net/gh/mashirozx/Sakura@3.2.7/images/hd.png"
        style="display: block; opacity: 1; width: 2559px; height: 1919px; margin-left: 0px; margin-top: -724.5px;">
    </div>
    <div id="login">
      <h1><a href="https://hyacm.com/"
             style="background-image: url(https://hyacm.com/wp-content/themes/Sakura/images/mashiro-logo-s.png); ">阿才的部落阁</a>
      </h1>

      <form name="loginform" id="loginform" action="https://hyacm.com/wp-login.php" method="post"
            onsubmit="return verificationOK();">
        <p>
          <label for="user_login">用户名或邮箱地址</label>
          <input type="text" name="log" id="user_login" class="input" value="" size="20" autocapitalize="off">
        </p>

        <div class="user-pass-wrap">
          <label for="user_pass">密码</label>
          <div class="wp-pwd">
            <input type="password" name="pwd" id="user_pass" class="input password-input" value="" size="20">
            <button type="button" class="button button-secondary wp-hide-pw hide-if-no-js" data-toggle="0"
                    aria-label="显示密码">
              <span class="dashicons dashicons-visibility" aria-hidden="true"></span>
            </button>
          </div>
        </div>
        <p>Login form protected by <a href="http://www.bad-neighborhood.com/login-lockdown.html">Login LockDown</a>.<br><br><br>
        </p>
        <p class="forgetmenot">Remember Me<input name="rememberme" id="rememberme" value="forever"
                                                 type="checkbox"><label for="rememberme"
                                                                        style="float: right;margin-top: 5px;transform: scale(2);margin-right: -10px;"></label>
        </p>
        <p></p>
        <div id="verification-slider">
          <div id="slider">
            <div id="slider_bg" style="width: 0px;"></div>
            <span id="label" style="left: 0px;">»</span><span id="labelTip">Slide to Verificate</span></div>
          <input type="hidden" name="verification" value="verification"></div>
        <p></p>
        <p class="submit">
          <input type="submit" name="wp-submit" id="wp-submit" class="button button-primary button-large" value="登录">
          <input type="hidden" name="redirect_to" value="https://hyacm.com/wp-admin/">
          <input type="hidden" name="testcookie" value="1">
        </p>
      </form>

      <p id="nav">
        <a href="https://hyacm.com/wp-login.php?action=register">注册</a> | <a
          href="https://hyacm.com/wp-login.php?action=lostpassword">忘记密码？</a>
      </p>
      <!--
      <script type="text/javascript">
        function wp_attempt_focus() {
          setTimeout(function () {
            try {
              d = document.getElementById("user_login");
              d.focus();
              d.select();
            } catch (er) {
            }
          }, 200);
        }

        wp_attempt_focus();
        if (typeof wpOnload === 'function') {
          wpOnload()
        }    </script>
        -->
      <p id="backtoblog"><a href="https://hyacm.com/">
        ← 返回到阿才的部落阁 </a></p>
      <div class="privacy-policy-page-link"><a class="privacy-policy-link"
                                               href="https://hyacm.com/privacy-policy/">隐私政策</a></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data: () => ({}),
}
</script>

<style scoped>

</style>