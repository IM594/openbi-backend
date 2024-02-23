package com.elec5619.bi.constant;


/**
 * Prompt constant
 * @Author Zhaohao Lu
 */
public interface PromptConstant {

    /**
     * Prompt Part1
     *
     * @Author Zhaohao Lu
     */
    String SHARED_INTRO = "You are a data analyst and front-end developer. Given the input format:\n" +
            "{Requirements or goals for data analysis},\n" +
            "{csv format raw data, separated by , },\n" +
            "generate the specified format in the following for a";

    /**
     * Prompt Part2 - for generating code
     *
     * @Author Zhaohao Lu
     */
    String SHARED_CODESPACE =
            "{front-end Echarts V5 option configuration object js code (must Wrap the attribute names and string values in double quotes in JSON data), ensuring reasonable and beautiful data visualization and no redundant content. Add tooltip for the chart so that I can switch it. Please make the charts look clear at a glance. And learn the official echarts example how to make a beautiful";

    /**
     * Prompt Part3 - for generating conclusion
     *
     * @Author Zhaohao Lu
     */
    String SHARED_CONCLUSION = "】】】】】（必须使用这五个符号作为分隔）" +
            "{Here, you must provide a clear and detailed conclusion of the data analysis, avoiding redundant comments}";


    /**
     * Default prompt
     *
     * @Author Zhaohao Lu
     */
    String DEFAULT_PROMPT = SHARED_INTRO + " chart...\n" +
            SHARED_CODESPACE + " chart. " +
            "For example: {Your Chart Configuration Here}...\n" +
            SHARED_CONCLUSION;

    /**
     * Line chart prompt
     *
     * @Author Zhaohao Lu
     */
    String LINE_CHART_PROMPT = SHARED_INTRO + " line chart...\n" +
            SHARED_CODESPACE + " line chart. " +
            "For example:\n" +
            "{\n" +
            "  \"xAxis\": {\n" +
            "    \"type\": \"category\",\n" +
            "    \"data\": [\"Mon\", \"Tue\", \"Wed\", \"Thu\", \"Fri\", \"Sat\", \"Sun\"]\n" +
            "  },\n" +
            "  \"yAxis\": {\n" +
            "    \"type\": \"value\"\n" +
            "  },\n" +
            "  \"series\": [\n" +
            "    {\n" +
            "      \"data\": [820, 932, 901, 934, 1290, 1330, 1320],\n" +
            "      \"type\": \"line\",\n" +
            "      \"smooth\": true\n" +
            "    }\n" +
            "  ]\n" +
            "}...\n" +
            SHARED_CONCLUSION;


    String BAR_CHART_PROMPT = SHARED_INTRO + " bar chart...\n" +
            SHARED_CODESPACE + " bar chart. " +
            "For example:\n" +
            "{\n" +
            "  \"xAxis\": {\n" +
            "    \"type\": \"category\",\n" +
            "    \"data\": [\"Mon\", \"Tue\", \"Wed\", \"Thu\", \"Fri\", \"Sat\", \"Sun\"]\n" +
            "  },\n" +
            "  \"yAxis\": {\n" +
            "    \"type\": \"value\"\n" +
            "  },\n" +
            "  \"series\": [\n" +
            "    {\n" +
            "      \"data\": [120, 200, 150, 80, 70, 110, 130],\n" +
            "      \"type\": \"bar\",\n" +
            "      \"showBackground\": true,\n" +
            "      \"backgroundStyle\": {\n" +
            "        \"color\": \"rgba(180, 180, 180, 0.2)\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}...\n" +
            SHARED_CONCLUSION;


    /**
     * Pie chart prompt
     *
     * @Author Zhaohao Lu
     */
    String PIE_CHART_PROMPT = SHARED_INTRO + " pie chart...\n" +
            SHARED_CODESPACE + " pie chart. " +
            "For example:\n" +
            "{\n" +
            "  \"tooltip\": {\n" +
            "    \"trigger\": \"item\"\n" +
            "  },\n" +
            "  \"legend\": {\n" +
            "    \"top\": \"5%\",\n" +
            "    \"left\": \"center\"\n" +
            "  },\n" +
            "  \"series\": [\n" +
            "    {\n" +
            "      \"name\": \"Access From\",\n" +
            "      \"type\": \"pie\",\n" +
            "      \"radius\": [\"40%\", \"70%\"],\n" +
            "      \"avoidLabelOverlap\": false,\n" +
            "      \"itemStyle\": {\n" +
            "        \"borderRadius\": 10,\n" +
            "        \"borderColor\": \"#fff\",\n" +
            "        \"borderWidth\": 2\n" +
            "      },\n" +
            "      \"label\": {\n" +
            "        \"show\": false,\n" +
            "        \"position\": \"center\"\n" +
            "      },\n" +
            "      \"emphasis\": {\n" +
            "        \"label\": {\n" +
            "          \"show\": true,\n" +
            "          \"fontSize\": 40,\n" +
            "          \"fontWeight\": \"bold\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"labelLine\": {\n" +
            "        \"show\": false\n" +
            "      },\n" +
            "      \"data\": [\n" +
            "        { \"value\": 1048, \"name\": \"Search Engine\" },\n" +
            "        { \"value\": 735, \"name\": \"Direct\" },\n" +
            "        { \"value\": 580, \"name\": \"Email\" },\n" +
            "        { \"value\": 484, \"name\": \"Union Ads\" },\n" +
            "        { \"value\": 300, \"name\": \"Video Ads\" }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}..." +
            SHARED_CONCLUSION;


    /**
     * Scatter chart prompt
     *
     * @Author Zhaohao Lu
     */
    String SCATTER_CHART_PROMPT = SHARED_INTRO + " scatter chart...\n" +
            SHARED_CODESPACE + " scatter chart. " +
            "For example:\n" +
            "{\n" +
            "  \"xAxis\": {},\n" +
            "  \"yAxis\": {},\n" +
            "  \"series\": [\n" +
            "    {\n" +
            "      \"symbolSize\": 20,\n" +
            "      \"data\": [\n" +
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
            "      \"type\": \"scatter\"\n" +
            "    }\n" +
            "  ]\n" +
            "}...\n" +
            SHARED_CONCLUSION;

}
