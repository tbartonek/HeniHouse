namespace HeniHouse.Designer
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.pDialog = new System.Windows.Forms.Panel();
            this.panel2 = new System.Windows.Forms.Panel();
            this.lbMessages = new System.Windows.Forms.ListBox();
            this.rbTransformerParams = new System.Windows.Forms.RadioButton();
            this.label1 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.panel3 = new System.Windows.Forms.Panel();
            this.lDate = new System.Windows.Forms.Label();
            this.lTime = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.rbDeviceConfiguration = new System.Windows.Forms.RadioButton();
            this.timerTime = new System.Windows.Forms.Timer(this.components);
            this.panel2.SuspendLayout();
            this.panel1.SuspendLayout();
            this.panel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // pDialog
            // 
            this.pDialog.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pDialog.Location = new System.Drawing.Point(0, 80);
            this.pDialog.Name = "pDialog";
            this.pDialog.Size = new System.Drawing.Size(1191, 590);
            this.pDialog.TabIndex = 5;
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.lbMessages);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panel2.Location = new System.Drawing.Point(0, 670);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(1191, 100);
            this.panel2.TabIndex = 6;
            // 
            // lbMessages
            // 
            this.lbMessages.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lbMessages.FormattingEnabled = true;
            this.lbMessages.Location = new System.Drawing.Point(0, 0);
            this.lbMessages.Name = "lbMessages";
            this.lbMessages.Size = new System.Drawing.Size(1191, 100);
            this.lbMessages.TabIndex = 0;
            // 
            // rbTransformerParams
            // 
            this.rbTransformerParams.AutoSize = true;
            this.rbTransformerParams.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rbTransformerParams.Location = new System.Drawing.Point(645, 53);
            this.rbTransformerParams.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.rbTransformerParams.Name = "rbTransformerParams";
            this.rbTransformerParams.Size = new System.Drawing.Size(145, 23);
            this.rbTransformerParams.TabIndex = 4;
            this.rbTransformerParams.Text = "Konfigurace Akcí";
            this.rbTransformerParams.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.Font = new System.Drawing.Font("Tahoma", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(356, 9);
            this.label1.MinimumSize = new System.Drawing.Size(480, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(480, 29);
            this.label1.TabIndex = 2;
            this.label1.Text = "Řešení chytrého domu";
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.White;
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.panel1.Controls.Add(this.panel3);
            this.panel1.Controls.Add(this.rbTransformerParams);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Controls.Add(this.pictureBox1);
            this.panel1.Controls.Add(this.rbDeviceConfiguration);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1191, 80);
            this.panel1.TabIndex = 4;
            // 
            // panel3
            // 
            this.panel3.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.panel3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel3.Controls.Add(this.lDate);
            this.panel3.Controls.Add(this.lTime);
            this.panel3.Location = new System.Drawing.Point(1034, 3);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(150, 73);
            this.panel3.TabIndex = 5;
            // 
            // lDate
            // 
            this.lDate.AutoSize = true;
            this.lDate.BackColor = System.Drawing.Color.Transparent;
            this.lDate.Font = new System.Drawing.Font("Arial", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lDate.ForeColor = System.Drawing.Color.Black;
            this.lDate.Location = new System.Drawing.Point(3, 40);
            this.lDate.Name = "lDate";
            this.lDate.Size = new System.Drawing.Size(37, 29);
            this.lDate.TabIndex = 3;
            this.lDate.Text = "---";
            // 
            // lTime
            // 
            this.lTime.AutoSize = true;
            this.lTime.BackColor = System.Drawing.Color.Transparent;
            this.lTime.Font = new System.Drawing.Font("Arial", 21.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lTime.ForeColor = System.Drawing.Color.Black;
            this.lTime.Location = new System.Drawing.Point(3, 6);
            this.lTime.Name = "lTime";
            this.lTime.Size = new System.Drawing.Size(45, 34);
            this.lTime.TabIndex = 2;
            this.lTime.Text = "---";
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(12, 21);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(283, 43);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 1;
            this.pictureBox1.TabStop = false;
            // 
            // rbDeviceConfiguration
            // 
            this.rbDeviceConfiguration.AutoSize = true;
            this.rbDeviceConfiguration.Checked = true;
            this.rbDeviceConfiguration.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rbDeviceConfiguration.Location = new System.Drawing.Point(361, 53);
            this.rbDeviceConfiguration.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.rbDeviceConfiguration.Name = "rbDeviceConfiguration";
            this.rbDeviceConfiguration.Size = new System.Drawing.Size(227, 23);
            this.rbDeviceConfiguration.TabIndex = 0;
            this.rbDeviceConfiguration.TabStop = true;
            this.rbDeviceConfiguration.Text = "Návrh a konfigurace zařízení";
            this.rbDeviceConfiguration.UseVisualStyleBackColor = true;
            this.rbDeviceConfiguration.CheckedChanged += new System.EventHandler(this.rbDeviceConfiguration_CheckedChanged);
            // 
            // timerTime
            // 
            this.timerTime.Enabled = true;
            this.timerTime.Interval = 1000;
            this.timerTime.Tick += new System.EventHandler(this.timerTime_Tick);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1191, 770);
            this.Controls.Add(this.pDialog);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.panel1);
            this.Name = "MainForm";
            this.Text = "Form1";
            this.panel2.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.panel3.ResumeLayout(false);
            this.panel3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel pDialog;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.ListBox lbMessages;
        private System.Windows.Forms.RadioButton rbTransformerParams;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Label lDate;
        private System.Windows.Forms.Label lTime;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.RadioButton rbDeviceConfiguration;
        private System.Windows.Forms.Timer timerTime;
    }
}

