<template>
  <v-container id="dashboard-view" fluid tag="section">
    <v-row>
      <v-col cols="12">
        <v-row>
          <v-col v-for="(chart, i) in charts" :key="`chart-${i}`" cols="12" md="6" lg="4">
            <material-chart-card
              :color="chart.color"
              :data="chart.data"
              :options="chart.options"
              :responsive-options="chart.responsiveOptions"
              :title="chart.title"
              :type="chart.type"
            >
              <template #subtitle>
                <div class="font-weight-light text--secondary">
                  <div v-html="chart.subtitle"/>
                </div>
              </template>

              <template #actions>
                <v-icon class="mr-1" small>mdi-clock-outline</v-icon>

                <span class="text-caption grey--text font-weight-light" v-text="chart.time"/>
              </template>
            </material-chart-card>
          </v-col>
        </v-row>
      </v-col>

      <v-col
        v-for="({ actionIcon, actionText, ...attrs }, i) in stats"
        :key="i"
        cols="12"
        md="6"
        lg="3"
      >
        <material-stat-card v-bind="attrs">
          <template #actions>
            <v-icon class="mr-2" small v-text="actionIcon"/>
            <div class="text-truncate">
              {{ actionText }}
            </div>
          </template>
        </material-stat-card>
      </v-col>

      <v-col cols="12" lg="4" style="max-height: 700px">
        <material-card color="primary" icon="mdi-account-outline">
          <template #title>
             <small class="text-body-1">Browser Statistic</small>
          </template>
          <div class="pa-4">
            <chartist
              class="pie-height"
              :data="{labels: ['Q1', 'Q2', 'Q3', 'Q4'],series: [15, 40, 60, 100]}"
              :options="{distributeSeries: true}"
              type="Pie"
            />
          </div>
          <template #actions>
            <v-icon class="mr-2" small v-text="'mdi-alert'"/>
            <div class="text-truncate">
              <div class="px-3 pb-3">
                <div class="text-body-2 text-uppercase grey--text font-weight-bold mb-3"> Legend</div>
                <div class="row ma-0 align-center">
                  <div class="v-avatar mr-1 info" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Apple</span>
                  <div class="v-avatar mr-1 warning" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Samsung</span>
                  <div class="v-avatar mr-1 red" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Windows Phone</span></div>
              </div>
            </div>
          </template>
        </material-card>
      </v-col>
      <v-col cols="12" lg="4" style="max-height: 700px">
        <material-card color="primary" icon="mdi-account-outline">
          <template #title>
            <small class="text-body-1">Browser Statistic</small>
          </template>
          <div class="pa-4">
            <chartist
              class="pie-height"
              :data="{labels: ['Q1', 'Q2', 'Q3', 'Q4'],series: [15, 40, 60, 100]}"
              :options="{distributeSeries: true}"
              type="Pie"
            />
          </div>
          <template #actions>
            <v-icon class="mr-2" small v-text="'mdi-alert'"/>
            <div class="text-truncate">
              <div class="px-3 pb-3">
                <div class="text-body-2 text-uppercase grey--text font-weight-bold mb-3"> Legend</div>
                <div class="row ma-0 align-center">
                  <div class="v-avatar mr-1 info" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Apple</span>
                  <div class="v-avatar mr-1 warning" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Samsung</span>
                  <div class="v-avatar mr-1 red" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Windows Phone</span></div>
              </div>
            </div>
          </template>
        </material-card>
      </v-col>
      <v-col cols="12" lg="4" style="max-height: 700px">
        <material-card color="primary" icon="mdi-account-outline">
          <template #title>
            <small class="text-body-1">Browser Statistic</small>
          </template>
          <div class="pa-4">
            <chartist
              class="pie-height"
              :data="{labels: ['Q1', 'Q2', 'Q3', 'Q4'],series: [15, 40, 60, 100]}"
              :options="{distributeSeries: true}"
              type="Pie"
            />
          </div>
          <template #actions>
            <v-icon class="mr-2" small v-text="'mdi-alert'"/>
            <div class="text-truncate">
              <div class="px-3 pb-3">
                <div class="text-body-2 text-uppercase grey--text font-weight-bold mb-3"> Legend</div>
                <div class="row ma-0 align-center">
                  <div class="v-avatar mr-1 info" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Apple</span>
                  <div class="v-avatar mr-1 warning" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Samsung</span>
                  <div class="v-avatar mr-1 red" style="height: 12px; min-width: 12px; width: 12px;"></div>
                  <span class="mr-3 font-weight-light">Windows Phone</span></div>
              </div>
            </div>
          </template>
        </material-card>
      </v-col>

      <v-col cols="12" md="6">
        <material-card color="orange" full-header>
          <template #heading>
            <div class="pa-8 white--text">
              <div class="text-h4 font-weight-light">
                Employees Stats
              </div>
              <div class="text-caption">
                New employees on 15th September, 2016
              </div>
            </div>
          </template>
          <v-card-text>
            <v-data-table :headers="headers" :items="items"/>
          </v-card-text>
        </material-card>
      </v-col>

      <v-col cols="12" md="6">
        <material-card color="accent" full-header>
          <template #heading>
            <v-tabs v-model="tabs" background-color="transparent" slider-color="white" class="pa-8">
              <span class="subheading font-weight-light mx-3" style="align-self: center">Tasks:</span>
              <v-tab class="mr-3">
                <v-icon class="mr-2">mdi-bug</v-icon>
                Bugs
              </v-tab>
              <v-tab class="mr-3">
                <v-icon class="mr-2">mdi-code-tags</v-icon>
                Website
              </v-tab>
              <v-tab>
                <v-icon class="mr-2">mdi-cloud</v-icon>
                Server
              </v-tab>
            </v-tabs>
          </template>
          <v-tabs-items v-model="tabs" background-color="transparent">
            <v-tab-item v-for="n in 3" :key="n">
              <v-card-text>
                <template v-for="(task, i) in tasks[tabs]">
                  <v-row :key="i" align="center" class="flex-nowrap">
                    <v-col cols="1">
                      <v-list-item-action>
                        <v-simple-checkbox
                          v-model="task.value"
                          color="secondary"
                        />
                      </v-list-item-action>
                    </v-col>

                    <v-col class="font-weight-light" cols="8" v-text="task.text"/>

                    <v-col cols="auto" class="text-right">
                      <v-icon class="mx-1">mdi-pencil</v-icon>

                      <v-icon class="mx-1" color="error">mdi-close</v-icon>
                    </v-col>
                  </v-row>
                </template>
              </v-card-text>
            </v-tab-item>
          </v-tabs-items>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// Utilities
import {get} from 'vuex-pathify'
import Vue from 'vue'
import MaterialCard from "../components/MaterialCard";
import {indexInit} from "../api/summary";

const lineSmooth = Vue.chartist.Interpolation.cardinal({
  tension: 0,
})

export default {
  name: 'DashboardView',
  components: {MaterialCard},
  data: () => ({
    charts: [
      {
        type: 'Bar',
        color: 'primary',
        title: 'Website Views',
        subtitle: 'Last Campaign Performance',
        time: 'updated 10 minutes ago',
        data: {
          labels: ['Ja', 'Fe', 'Ma', 'Ap', 'Mai', 'Ju', 'Jul', 'Au', 'Se', 'Oc', 'No', 'De'],
          series: [
            [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895],
          ],
        },
        options: {
          axisX: {
            showGrid: false,
          },
          low: 0,
          high: 1000,
          chartPadding: {
            top: 0,
            right: 5,
            bottom: 0,
            left: 0,
          },
        },
        responsiveOptions: [
          ['screen and (max-width: 640px)', {
            seriesBarDistance: 5,
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0]
              },
            },
          }],
        ],
      }, {
        type: 'Line',
        color: 'success',
        title: 'Comments',
        subtitle: '<i class="mdi mdi-arrow-up green--text"></i><span class="green--text">55%</span>&nbsp;increase in today\'s sales',
        time: 'updated 4 minutes ago',
        data: {
          labels: ['12am', '3pm', '6pm', '9pm', '12pm', '3am', '6am', '9am'],
          series: [
            [230, 750, 450, 300, 280, 240, 200, 190],
          ],
        },
        options: {
          lineSmooth,
          low: 0,
          high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0,
          },
        },
      }, {
        type: 'Pie',
        color: 'info',
        title: '7天访问量',
        subtitle: 'Last Campaign Performance',
        time: 'campaign sent 26 minutes ago',
        data: {
          labels: ['Q1', 'Q2', 'Q3', 'Q4'],
          series: [15, 40, 60, 100]
        },
        options: {
          distributeSeries: true
        },
      }],
    headers: [
      {
        sortable: false,
        text: 'ID',
        value: 'id',
      },
      {
        sortable: false,
        text: 'Name',
        value: 'name',
      },
      {
        sortable: false,
        text: 'Salary',
        value: 'salary',
        align: 'right',
      },
      {
        sortable: false,
        text: 'Country',
        value: 'country',
        align: 'right',
      },
      {
        sortable: false,
        text: 'City',
        value: 'city',
        align: 'right',
      },
    ],
    items: [
      {
        id: 1,
        name: 'Dakota Rice',
        country: 'Niger',
        city: 'Oud-Tunrhout',
        salary: '$35,738',
      },
      {
        id: 2,
        name: 'Minerva Hooper',
        country: 'Curaçao',
        city: 'Sinaai-Waas',
        salary: '$23,738',
      },
      {
        id: 3,
        name: 'Sage Rodriguez',
        country: 'Netherlands',
        city: 'Overland Park',
        salary: '$56,142',
      },
      {
        id: 4,
        name: 'Philip Chanley',
        country: 'Korea, South',
        city: 'Gloucester',
        salary: '$38,735',
      },
      {
        id: 5,
        name: 'Doris Greene',
        country: 'Malawi',
        city: 'Feldkirchen in Kārnten',
        salary: '$63,542',
      },
    ],
    stats: [
      {
        actionIcon: 'mdi-alert',
        actionText: 'Get More Space...',
        color: '#FD9A13',
        icon: 'mdi-sofa-single',
        title: '今日访客',
        value: '184',
      },
      {
        actionIcon: 'mdi-tag',
        actionText: 'Tracked from Google Analytics',
        color: 'primary',
        icon: 'mdi-chart-bar',
        title: '总访客',
        value: '75.521',
      },
      {
        actionIcon: 'mdi-calendar-range',
        actionText: 'Last 24 Hours',
        color: 'success',
        icon: 'mdi-store',
        title: '评论数',
        value: '$34,245',
      },
      {
        actionIcon: 'mdi-history',
        actionText: 'Just Updated',
        color: 'info',
        icon: 'mdi-twitter',
        title: '用户数量',
        value: '+245',
      },
    ],
    tabs: 0,
    tasks: {
      0: [
        {
          text: 'Sign contract for "What are conference organizers afraid of?"',
          value: true,
        },
        {
          text: 'Lines From Great Russian Literature? Or E-mails From My Boss?',
          value: false,
        },
        {
          text: 'Flooded: One year later, assessing what was lost and what was found when a ravaging rain swept through metro Detroit',
          value: false,
        },
        {
          text: 'Create 4 Invisible User Experiences you Never Knew About',
          value: true,
        },
      ],
      1: [
        {
          text: 'Flooded: One year later, assessing what was lost and what was found when a ravaging rain swept through metro Detroit',
          value: true,
        },
        {
          text: 'Sign contract for "What are conference organizers afraid of?"',
          value: false,
        },
      ],
      2: [
        {
          text: 'Lines From Great Russian Literature? Or E-mails From My Boss?',
          value: false,
        },
        {
          text: 'Flooded: One year later, assessing what was lost and what was found when a ravaging rain swept through metro Detroit',
          value: true,
        },
        {
          text: 'Sign contract for "What are conference organizers afraid of?"',
          value: true,
        },
      ],
    },
  }),
  methods: {

  },
  computed: {
    sales: get('sales/sales'),
    totalSales() {
      return this.sales.reduce((acc, val) => acc + val.salesInM, 0)
    },
  },
  created() {
    indexInit().then(response => {
      console.log(response.data);
      let data = response.data;
      this.stats[0].value = data.todayVisitor.toString();
      this.stats[1].value = data.totalVisitor.toString();
      this.stats[2].value = data.commentCount.toString();
      this.stats[3].value = data.userCount.toString();
    })
  }
}
</script>
<style scoped>
.pie-height{
  max-height: 400px;
}
</style>
