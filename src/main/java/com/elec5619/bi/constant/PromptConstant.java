package com.elec5619.bi.constant;


/**
 * Prompt constant
 * @Author Zhaohao Lu
 */
public interface PromptConstant {
    String SHARED_INTRO = "You are a data analyst and front-end developer. Given the input format:\n" +
            "{Requirements or goals for data analysis},\n" +
            "{csv format raw data, separated by , },\n" +
            "generate the specified format in the following for a";

    String SHARED_CODESPACE = "【【【【【\n" +
            "{front-end Echarts V5 option configuration object js code, ensuring reasonable and beautiful data visualization and no redundant content. Add tooltip for the chart so that I can switch it. Please make the charts look clear at a glance. And learn the official echarts example how to make a beautiful";

    String SHARED_CONCLUSION = "】】】】】\n" +
            "{Provide a clear and detailed conclusion of the data analysis, avoiding redundant comments}";




    String DEFAULT_PROMPT = SHARED_INTRO + " chart...\n" +
            SHARED_CODESPACE + " chart. " +
            "For example: {Your Chart Configuration Here}...\n" +
            SHARED_CONCLUSION;

    String LINE_CHART_PROMPT = SHARED_INTRO + " line chart...\n" +
            SHARED_CODESPACE + " line chart. " +
            "For example:\n" +
            "{\n" +
            "  xAxis: {\n" +
            "    type: 'category',\n" +
            "    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n" +
            "  },\n" +
            "  yAxis: {\n" +
            "    type: 'value'\n" +
            "  },\n" +
            "  series: [\n" +
            "    {\n" +
            "      data: [820, 932, 901, 934, 1290, 1330, 1320],\n" +
            "      type: 'line',\n" +
            "      smooth: true\n" +
            "    }\n" +
            "  ]\n" +
            "}...\n" +
            SHARED_CONCLUSION;

    String BAR_CHART_PROMPT = SHARED_INTRO + " bar chart...\n" +
            SHARED_CODESPACE + " bar chart. " +
            "For example:\n" +
            "{\n" +
            "  xAxis: {\n" +
            "    type: 'category',\n" +
            "    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n" +
            "  },\n" +
            "  yAxis: {\n" +
            "    type: 'value'\n" +
            "  },\n" +
            "  series: [\n" +
            "    {\n" +
            "      data: [120, 200, 150, 80, 70, 110, 130],\n" +
            "      type: 'bar',\n" +
            "      showBackground: true,\n" +
            "      backgroundStyle: {\n" +
            "        color: 'rgba(180, 180, 180, 0.2)'\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}...\n" +
            SHARED_CONCLUSION;

    String PIE_CHART_PROMPT = SHARED_INTRO + " pie chart...\n" +
            SHARED_CODESPACE + " pie chart. " +
            SHARED_CONCLUSION;

    String SCATTER_CHART_PROMPT = SHARED_INTRO + " scatter chart...\n" +
            SHARED_CODESPACE + " scatter chart. " +
            "For example:\n" +
            "{\n" +
            "  xAxis: {},\n" +
            "  yAxis: {},\n" +
            "  series: [\n" +
            "    {\n" +
            "      symbolSize: 20,\n" +
            "      data: [\n" +
            "        [1, 8],\n" +
            "        [8.07, 6.95],\n" +
            "        [13.0, 7.58],\n" +
            "        [9.05, 8.81],\n" +
            "        [11.0, 8.33],\n" +
            "        [14.0, 7.66],\n" +
            "        [13.4, 6.81],\n" +
            "        [10.0, 6.33],\n" +
            "        [14.0, 8.96],\n" +
            "        [12.5, 6.82],\n" +
            "        [9.15, 7.2],\n" +
            "        [11.5, 7.2],\n" +
            "        [3.03, 4.23],\n" +
            "        [12.2, 7.83],\n" +
            "        [2.02, 4.47],\n" +
            "        [1.05, 3.33],\n" +
            "        [4.05, 4.96],\n" +
            "        [6.03, 7.24],\n" +
            "        [12.0, 6.26],\n" +
            "        [12.0, 8.84],\n" +
            "        [7.08, 5.82],\n" +
            "        [5.02, 5.68]\n" +
            "      ],\n" +
            "      type: 'scatter'\n" +
            "    }\n" +
            "  ]\n" +
            "}...\n" +
            SHARED_CONCLUSION;
}
