### 数据库修改
1. 标准物资开票表 bus_standard_invoice_out增加freeze_quantity（冻结数量这一列）。
2. 标准物资开票表 bus_standard_invoice_out的主键为{行项目、采购订单号、物料凭证}。

3. 标准物资发票表bus_standard_invoice的status类型由varchar(0)改为varchar(50)

4. 前端项目中.eslintrc.js加入一行配置："no-unused-vars": 'off'

5. 寄售物资发票表中invoice_status 0：已提交 1：已挂帐
