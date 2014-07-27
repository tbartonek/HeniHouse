using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;

namespace HeniHouse.Designer
{
    public class cMotionDetector:UserControl
    {
        private Label label1;
        public cMotionDetector()
        {
            InitializeComponent();
        }
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.BackColor = System.Drawing.Color.PaleTurquoise;
            this.label1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label1.Location = new System.Drawing.Point(0, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(55, 45);
            this.label1.TabIndex = 0;
            this.label1.Text = "Detektor pohybu";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cMotionDetector
            // 
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.label1);
            this.Name = "cMotionDetector";
            this.Size = new System.Drawing.Size(55, 45);
            this.ResumeLayout(false);

        }
    }
}
