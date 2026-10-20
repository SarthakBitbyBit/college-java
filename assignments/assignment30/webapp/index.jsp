<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>assignment 30 - jsp crud operations</title>
    <style>
        body { font-family: sans-serif; background: #f1f5f9; margin: 40px; color: #0f172a; }
        .card { background: #ffffff; padding: 28px; border-radius: 8px; max-width: 700px; margin: 0 auto; box-shadow: 0 4px 12px rgba(0,0,0,0.06); }
        h1 { font-size: 22px; margin-top: 0; border-bottom: 2px solid #e2e8f0; padding-bottom: 12px; }
        p { color: #64748b; font-size: 14px; line-height: 1.5; }
        .grid { display: flex; gap: 20px; margin-top: 24px; }
        .box { flex: 1; background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 18px; text-align: center; }
        .box h3 { margin-top: 0; font-size: 16px; color: #1e293b; }
        .box p { font-size: 13px; color: #64748b; margin-bottom: 16px; min-height: 38px; }
        .btn { display: inline-block; padding: 9px 18px; background: #2563eb; color: #fff; text-decoration: none; border-radius: 4px; font-weight: bold; font-size: 14px; }
        .btn:hover { background: #1d4ed8; }
        .meta { margin-top: 24px; background: #e0f2fe; border: 1px solid #bae6fd; border-radius: 6px; padding: 12px; font-size: 13px; color: #0369a1; }
    </style>
</head>
<body>
    <div class="card">
        <h1>assignment 30: jsp crud operations</h1>
        <p>demonstrates create, read, update, and delete (crud) database operations using jsp, jdbc, and mysql parameterized preparedstatement queries.</p>

        <div class="grid">
            <div class="box">
                <h3>exercise 1: employee management</h3>
                <p>manage employee records: id, name, department, salary.</p>
                <a class="btn" href="employees.jsp">launch employee crud</a>
            </div>
            <div class="box">
                <h3>exercise 2: patient management</h3>
                <p>manage patient records: id, name, age, disease, treatment status.</p>
                <a class="btn" href="patients.jsp">launch patient crud</a>
            </div>
        </div>

        <div class="meta">
            <strong>database configuration:</strong> database: <code>jsp_lab</code> | user: <code>javauser</code> | table engine: <code>mysql 8.4</code>
        </div>
    </div>
</body>
</html>
