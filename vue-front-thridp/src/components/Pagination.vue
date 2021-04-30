<template>

</template>

<script>
export default {
  name: "Pagination",
  data: () => ({}),
  computed: {
    pageRange(){
      let range = [];
      let cur = this.currentPage;
      let v = 3;
      let s=cur-v+1;
      if (s<=0)s=1
      let e = cur+v;
      if (e>=this.totalPage)e=this.totalPage+1
      for (;s<e;s++){//todo
        range.push(s);
      }
      return range;
    }
  }
}
</script>

<style scoped>
.next,.prev{
  color: #00bbff;
}
</style>