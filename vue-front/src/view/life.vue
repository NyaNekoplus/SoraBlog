<template>
  <div>
    {{comment}}
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "life",
  data(){
    return{
      comment:""
    };
  },
  mounted() {
    request.get(process.env.FRONT_API+'/comment/test').then(response=>(this.comment=response.data));
  }
}
</script>

<style scoped>

</style>