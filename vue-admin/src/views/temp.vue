<template>
  <div class="col-lg-6 col-12">
    <div class="v-card--material mt-4 v-card v-sheet theme--light" id="multiple-bar">
      <div class="v-card__title align-start">
        <div
          class="overflow-hidden mt-n9 transition-swing v-card--material__sheet v-sheet theme--light elevation-6 rounded accent"
          style="max-width: 100%;">
          <div class="pa-7"><i aria-hidden="true" class="v-icon notranslate mdi mdi-poll-box theme--dark"></i></div>
        </div>
        <div class="text-h4 pl-4 v-card--material__title">
          <div class="text-h4 font-weight-light"> Multiple Bars Chart <span
            class="text-subtitle-1">— Bar Chart</span></div><!---->
          <div class="text-subtitle-1 mb-n2"><!----></div>
        </div>
      </div>
      <div class="mt-3 ct-square" style="max-height: 150px;">
        <svg xmlns:ct="http://gionkunz.github.com/chartist-js/ct" width="100%" height="100%" class="ct-chart-bar"
             style="width: 100%; height: 100%;">
          <g class="ct-grids">
            <line y1="120" y2="120" x1="40" x2="1091.5" class="ct-grid ct-vertical"></line>
            <line y1="93.33333333333333" y2="93.33333333333333" x1="40" x2="1091.5"
                  class="ct-grid ct-vertical"></line>
            <line y1="66.66666666666666" y2="66.66666666666666" x1="40" x2="1091.5"
                  class="ct-grid ct-vertical"></line>
            <line y1="40" y2="40" x1="40" x2="1091.5" class="ct-grid ct-vertical"></line>
            <line y1="13.333333333333329" y2="13.333333333333329" x1="40" x2="1091.5"
                  class="ct-grid ct-vertical"></line>
          </g>
          <g>
            <g class="ct-series ct-series-a">
              <line x1="78.8125" x2="78.8125" y1="120" y2="47.733333333333334" class="ct-bar" ct:value="542"></line>
              <line x1="166.4375" x2="166.4375" y1="120" y2="60.93333333333333" class="ct-bar"
                    ct:value="443"></line>
              <line x1="254.0625" x2="254.0625" y1="120" y2="77.33333333333334" class="ct-bar"
                    ct:value="320"></line>
              <line x1="341.6875" x2="341.6875" y1="120" y2="16" class="ct-bar" ct:value="780"></line>
              <line x1="429.3125" x2="429.3125" y1="120" y2="46.266666666666666" class="ct-bar"
                    ct:value="553"></line>
              <line x1="516.9375" x2="516.9375" y1="120" y2="59.6" class="ct-bar" ct:value="453"></line>
              <line x1="604.5625" x2="604.5625" y1="120" y2="76.53333333333333" class="ct-bar"
                    ct:value="326"></line>
              <line x1="692.1875" x2="692.1875" y1="120" y2="62.13333333333333" class="ct-bar"
                    ct:value="434"></line>
              <line x1="779.8125" x2="779.8125" y1="120" y2="44.266666666666666" class="ct-bar"
                    ct:value="568"></line>
              <line x1="867.4375" x2="867.4375" y1="120" y2="38.66666666666667" class="ct-bar"
                    ct:value="610"></line>
              <line x1="955.0625" x2="955.0625" y1="120" y2="19.200000000000003" class="ct-bar"
                    ct:value="756"></line>
              <line x1="1042.6875" x2="1042.6875" y1="120" y2="0.6666666666666714" class="ct-bar"
                    ct:value="895"></line>
            </g>
            <g class="ct-series ct-series-b">
              <line x1="88.8125" x2="88.8125" y1="120" y2="66.66666666666666" class="ct-bar" ct:value="400"></line>
              <line x1="176.4375" x2="176.4375" y1="120" y2="93.33333333333333" class="ct-bar"
                    ct:value="200"></line>
              <line x1="264.0625" x2="264.0625" y1="120" y2="86.66666666666666" class="ct-bar"
                    ct:value="250"></line>
              <line x1="351.6875" x2="351.6875" y1="120" y2="43.33333333333333" class="ct-bar"
                    ct:value="575"></line>
              <line x1="439.3125" x2="439.3125" y1="120" y2="60" class="ct-bar" ct:value="450"></line>
              <line x1="526.9375" x2="526.9375" y1="120" y2="80" class="ct-bar" ct:value="300"></line>
              <line x1="614.5625" x2="614.5625" y1="120" y2="82" class="ct-bar" ct:value="285"></line>
              <line x1="702.1875" x2="702.1875" y1="120" y2="70.66666666666666" class="ct-bar"
                    ct:value="370"></line>
              <line x1="789.8125" x2="789.8125" y1="120" y2="70.66666666666666" class="ct-bar"
                    ct:value="370"></line>
              <line x1="877.4375" x2="877.4375" y1="120" y2="65.33333333333334" class="ct-bar"
                    ct:value="410"></line>
              <line x1="965.0625" x2="965.0625" y1="120" y2="37.33333333333333" class="ct-bar"
                    ct:value="620"></line>
              <line x1="1052.6875" x2="1052.6875" y1="120" y2="28" class="ct-bar" ct:value="690"></line>
            </g>
          </g>
          <g class="ct-labels">
            <foreignObject style="overflow: visible;" x="40" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Jan</span></foreignObject>
            <foreignObject style="overflow: visible;" x="127.625" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Feb</span></foreignObject>
            <foreignObject style="overflow: visible;" x="215.25" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Mar</span></foreignObject>
            <foreignObject style="overflow: visible;" x="302.875" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Apr</span></foreignObject>
            <foreignObject style="overflow: visible;" x="390.5" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">May</span></foreignObject>
            <foreignObject style="overflow: visible;" x="478.125" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Jun</span></foreignObject>
            <foreignObject style="overflow: visible;" x="565.75" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Jul</span></foreignObject>
            <foreignObject style="overflow: visible;" x="653.375" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Aug</span></foreignObject>
            <foreignObject style="overflow: visible;" x="741" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Sep</span></foreignObject>
            <foreignObject style="overflow: visible;" x="828.625" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Oct</span></foreignObject>
            <foreignObject style="overflow: visible;" x="916.25" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Nov</span></foreignObject>
            <foreignObject style="overflow: visible;" x="1003.875" y="125" width="87.625" height="20"><span
              class="ct-label ct-horizontal ct-end" xmlns="http://www.w3.org/2000/xmlns/"
              style="width: 88px; height: 20px;">Dec</span></foreignObject>
            <foreignObject style="overflow: visible;" y="93.33333333333333" x="0" height="26.666666666666668"
                           width="30"><span class="ct-label ct-vertical ct-start"
                                            xmlns="http://www.w3.org/2000/xmlns/"
                                            style="height: 27px; width: 30px;">0</span></foreignObject>
            <foreignObject style="overflow: visible;" y="66.66666666666666" x="0" height="26.666666666666668"
                           width="30"><span class="ct-label ct-vertical ct-start"
                                            xmlns="http://www.w3.org/2000/xmlns/"
                                            style="height: 27px; width: 30px;">200</span></foreignObject>
            <foreignObject style="overflow: visible;" y="39.99999999999999" x="0" height="26.666666666666664"
                           width="30"><span class="ct-label ct-vertical ct-start"
                                            xmlns="http://www.w3.org/2000/xmlns/"
                                            style="height: 27px; width: 30px;">400</span></foreignObject>
            <foreignObject style="overflow: visible;" y="13.333333333333329" x="0" height="26.66666666666667"
                           width="30"><span class="ct-label ct-vertical ct-start"
                                            xmlns="http://www.w3.org/2000/xmlns/"
                                            style="height: 27px; width: 30px;">600</span></foreignObject>
            <foreignObject style="overflow: visible;" y="-16.66666666666667" x="0" height="30" width="30"><span
              class="ct-label ct-vertical ct-start" xmlns="http://www.w3.org/2000/xmlns/"
              style="height: 30px; width: 30px;">800</span></foreignObject>
          </g>
        </svg>
      </div><!---->
    </div>
    <div class="py-3"></div>
    <div class="v-card--material mt-4 v-card v-sheet theme--light" id="pie">
      <div class="v-card__title align-start">
        <div
          class="overflow-hidden mt-n9 transition-swing v-card--material__sheet v-sheet theme--light elevation-6 rounded accent"
          style="max-width: 100%;">
          <div class="pa-7"><i aria-hidden="true" class="v-icon notranslate mdi mdi-chart-pie theme--dark"></i>
          </div>
        </div>
        <div class="text-h4 pl-4 v-card--material__title"> Pie Chart
          <div class="text-subtitle-1 mb-n2"><!----></div>
        </div>
      </div>
      <div class="ct-square" style="max-height: 250px;">
        <svg xmlns:ct="http://gionkunz.github.com/chartist-js/ct" width="100%" height="100%" class="ct-chart-pie"
             style="width: 100%; height: 100%;">
          <g class="ct-series ct-series-a">
            <path d="M466.104,212.476A120,120,0,1,0,548.25,5L548.25,125Z" class="ct-slice-pie" ct:value="62"></path>
          </g>
          <g class="ct-series ct-series-b">
            <path d="M504.075,13.427A120,120,0,0,0,466.41,212.762L548.25,125Z" class="ct-slice-pie"
                  ct:value="32"></path>
          </g>
          <g class="ct-series ct-series-c">
            <path d="M548.25,5A120,120,0,0,0,503.686,13.582L548.25,125Z" class="ct-slice-pie" ct:value="6"></path>
          </g>
          <g>
            <text dx="604.036589153295" dy="147.08747316108068" text-anchor="middle" class="ct-label">62%</text>
            <text dx="489.31276495627867" dy="113.7571211248566" text-anchor="middle" class="ct-label">32%</text>
            <text dx="537.0071211248564" dy="66.0627649562787" text-anchor="middle" class="ct-label">6%</text>
          </g>
        </svg>
      </div>
      <hr role="separator" aria-orientation="horizontal" class="ma-3 v-divider theme--light">
      <div class="px-3 pb-3">
        <div class="text-body-2 text-uppercase grey--text font-weight-bold mb-3"> Legend</div>
        <div class="row ma-0 align-center">
          <div class="v-avatar mr-1 info" style="height: 12px; min-width: 12px; width: 12px;"></div>
          <span class="mr-3 font-weight-light">Apple</span>
          <div class="v-avatar mr-1 warning" style="height: 12px; min-width: 12px; width: 12px;"></div>
          <span class="mr-3 font-weight-light">Samsung</span>
          <div class="v-avatar mr-1 red" style="height: 12px; min-width: 12px; width: 12px;"></div>
          <span class="mr-3 font-weight-light">Windows Phone</span></div>
      </div><!----></div>
  </div>
</template>

<script>
export default {
  name: "temp",
  data: () => ({}),
}
</script>

<style scoped>

</style>
