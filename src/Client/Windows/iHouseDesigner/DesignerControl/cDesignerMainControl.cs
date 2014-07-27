// IMPORTANT: Read the license included with this code archive.
using System;
using System.Drawing;
using System.Drawing.Design;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.Design;
using System.ComponentModel.Design.Serialization;
using System.Windows.Forms;
using System.Windows.Forms.Design;
using System.Data;
using System.IO;

namespace HeniHouse.Designer.Hosting
{
	public class cDesignerMainControl : UserControl
	{

        private Dictionary<TreeNode, ControlCollection> mLayerDevices = new Dictionary<TreeNode, ControlCollection>();

		private System.Windows.Forms.PropertyGrid propertyGrid;
		private System.Windows.Forms.Splitter splitter1;
		private System.Windows.Forms.Panel pLeft;
		private System.Windows.Forms.Panel pnlViewHost;
		private System.Windows.Forms.Splitter splitter2;
		private ToolboxService lstToolbox;
        IDesignerHost host;
		private System.Windows.Forms.Label lblSelectedComponent;
		private System.Windows.Forms.MainMenu mainMenu1;
		private System.Windows.Forms.MenuItem menuItem1;
        private Label label2;
        private IContainer components;
        private Panel panel2;
        private TreeView tvDevices;
        private ToolStrip toolStrip1;
        private Label label1;
        private ToolStripButton tsAddLayer;
        private ToolStripButton tsAddSubNode;
        private ToolStripSeparator toolStripSeparator1;
        private ToolStripButton tsSaveXML;
        private ToolStripButton toolStripButton1;
        private ToolStripSeparator toolStripSeparator2;
        private ToolStripButton tsLoadXml;
        private System.Windows.Forms.MenuItem mnuDelete;

		public cDesignerMainControl()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			Initialize();
		}

		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(cDesignerMainControl));
            this.propertyGrid = new System.Windows.Forms.PropertyGrid();
            this.splitter1 = new System.Windows.Forms.Splitter();
            this.pLeft = new System.Windows.Forms.Panel();
            this.splitter2 = new System.Windows.Forms.Splitter();
            this.label2 = new System.Windows.Forms.Label();
            this.lblSelectedComponent = new System.Windows.Forms.Label();
            this.pnlViewHost = new System.Windows.Forms.Panel();
            this.mainMenu1 = new System.Windows.Forms.MainMenu(this.components);
            this.menuItem1 = new System.Windows.Forms.MenuItem();
            this.mnuDelete = new System.Windows.Forms.MenuItem();
            this.panel2 = new System.Windows.Forms.Panel();
            this.tvDevices = new System.Windows.Forms.TreeView();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.tsAddLayer = new System.Windows.Forms.ToolStripButton();
            this.tsAddSubNode = new System.Windows.Forms.ToolStripButton();
            this.label1 = new System.Windows.Forms.Label();
            this.tsSaveXML = new System.Windows.Forms.ToolStripButton();
            this.toolStripButton1 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.lstToolbox = new HeniHouse.Designer.Hosting.ToolboxService();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsLoadXml = new System.Windows.Forms.ToolStripButton();
            this.pLeft.SuspendLayout();
            this.panel2.SuspendLayout();
            this.toolStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // propertyGrid
            // 
            this.propertyGrid.Dock = System.Windows.Forms.DockStyle.Fill;
            this.propertyGrid.LineColor = System.Drawing.SystemColors.ScrollBar;
            this.propertyGrid.Location = new System.Drawing.Point(0, 270);
            this.propertyGrid.Name = "propertyGrid";
            this.propertyGrid.Size = new System.Drawing.Size(237, 245);
            this.propertyGrid.TabIndex = 0;
            // 
            // splitter1
            // 
            this.splitter1.Dock = System.Windows.Forms.DockStyle.Right;
            this.splitter1.Location = new System.Drawing.Point(820, 0);
            this.splitter1.Name = "splitter1";
            this.splitter1.Size = new System.Drawing.Size(4, 539);
            this.splitter1.TabIndex = 1;
            this.splitter1.TabStop = false;
            // 
            // pLeft
            // 
            this.pLeft.Controls.Add(this.propertyGrid);
            this.pLeft.Controls.Add(this.splitter2);
            this.pLeft.Controls.Add(this.lstToolbox);
            this.pLeft.Controls.Add(this.label2);
            this.pLeft.Controls.Add(this.lblSelectedComponent);
            this.pLeft.Dock = System.Windows.Forms.DockStyle.Left;
            this.pLeft.Location = new System.Drawing.Point(0, 0);
            this.pLeft.Name = "pLeft";
            this.pLeft.Size = new System.Drawing.Size(237, 539);
            this.pLeft.TabIndex = 2;
            // 
            // splitter2
            // 
            this.splitter2.Dock = System.Windows.Forms.DockStyle.Top;
            this.splitter2.Location = new System.Drawing.Point(0, 266);
            this.splitter2.Name = "splitter2";
            this.splitter2.Size = new System.Drawing.Size(237, 4);
            this.splitter2.TabIndex = 1;
            this.splitter2.TabStop = false;
            // 
            // label2
            // 
            this.label2.BackColor = System.Drawing.Color.CornflowerBlue;
            this.label2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label2.Dock = System.Windows.Forms.DockStyle.Top;
            this.label2.Font = new System.Drawing.Font("Tahoma", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label2.Location = new System.Drawing.Point(0, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(237, 25);
            this.label2.TabIndex = 4;
            this.label2.Text = "Paleta komponent";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblSelectedComponent
            // 
            this.lblSelectedComponent.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.lblSelectedComponent.Location = new System.Drawing.Point(0, 515);
            this.lblSelectedComponent.Name = "lblSelectedComponent";
            this.lblSelectedComponent.Size = new System.Drawing.Size(237, 24);
            this.lblSelectedComponent.TabIndex = 3;
            this.lblSelectedComponent.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // pnlViewHost
            // 
            this.pnlViewHost.BackColor = System.Drawing.SystemColors.Window;
            this.pnlViewHost.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlViewHost.Location = new System.Drawing.Point(237, 0);
            this.pnlViewHost.Name = "pnlViewHost";
            this.pnlViewHost.Size = new System.Drawing.Size(383, 539);
            this.pnlViewHost.TabIndex = 3;
            // 
            // mainMenu1
            // 
            this.mainMenu1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
            this.menuItem1});
            // 
            // menuItem1
            // 
            this.menuItem1.Index = 0;
            this.menuItem1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
            this.mnuDelete});
            this.menuItem1.Text = "&Edit";
            // 
            // mnuDelete
            // 
            this.mnuDelete.Index = 0;
            this.mnuDelete.Shortcut = System.Windows.Forms.Shortcut.Del;
            this.mnuDelete.Text = "&Delete";
            this.mnuDelete.Click += new System.EventHandler(this.mnuDelete_Click);
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.tvDevices);
            this.panel2.Controls.Add(this.toolStrip1);
            this.panel2.Controls.Add(this.label1);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Right;
            this.panel2.Location = new System.Drawing.Point(620, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(200, 539);
            this.panel2.TabIndex = 5;
            // 
            // tvDevices
            // 
            this.tvDevices.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tvDevices.LabelEdit = true;
            this.tvDevices.Location = new System.Drawing.Point(0, 50);
            this.tvDevices.Name = "tvDevices";
            this.tvDevices.Size = new System.Drawing.Size(200, 489);
            this.tvDevices.TabIndex = 1;
            this.tvDevices.AfterLabelEdit += new System.Windows.Forms.NodeLabelEditEventHandler(this.tvDevices_AfterLabelEdit);
            this.tvDevices.BeforeSelect += new System.Windows.Forms.TreeViewCancelEventHandler(this.tvDevices_BeforeSelect);
            this.tvDevices.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvDevices_AfterSelect);
            // 
            // toolStrip1
            // 
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsAddLayer,
            this.tsAddSubNode,
            this.toolStripSeparator1,
            this.tsSaveXML,
            this.toolStripButton1,
            this.toolStripSeparator2,
            this.tsLoadXml});
            this.toolStrip1.Location = new System.Drawing.Point(0, 25);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(200, 25);
            this.toolStrip1.TabIndex = 2;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // tsAddLayer
            // 
            this.tsAddLayer.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsAddLayer.Image = ((System.Drawing.Image)(resources.GetObject("tsAddLayer.Image")));
            this.tsAddLayer.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsAddLayer.Name = "tsAddLayer";
            this.tsAddLayer.Size = new System.Drawing.Size(23, 22);
            this.tsAddLayer.Text = "toolStripButton1";
            this.tsAddLayer.ToolTipText = "Add root node";
            this.tsAddLayer.Click += new System.EventHandler(this.tsAddLayer_Click);
            // 
            // tsAddSubNode
            // 
            this.tsAddSubNode.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsAddSubNode.Image = ((System.Drawing.Image)(resources.GetObject("tsAddSubNode.Image")));
            this.tsAddSubNode.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsAddSubNode.Name = "tsAddSubNode";
            this.tsAddSubNode.Size = new System.Drawing.Size(23, 22);
            this.tsAddSubNode.Text = "toolStripButton1";
            this.tsAddSubNode.ToolTipText = "Add sub node";
            this.tsAddSubNode.Click += new System.EventHandler(this.tsAddSubNode_Click);
            // 
            // label1
            // 
            this.label1.BackColor = System.Drawing.Color.CornflowerBlue;
            this.label1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label1.Dock = System.Windows.Forms.DockStyle.Top;
            this.label1.Font = new System.Drawing.Font("Tahoma", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label1.Location = new System.Drawing.Point(0, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(200, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Návrh zaøízení";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // tsSaveXML
            // 
            this.tsSaveXML.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsSaveXML.Image = ((System.Drawing.Image)(resources.GetObject("tsSaveXML.Image")));
            this.tsSaveXML.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsSaveXML.Name = "tsSaveXML";
            this.tsSaveXML.Size = new System.Drawing.Size(23, 22);
            this.tsSaveXML.Text = "toolStripButton1";
            this.tsSaveXML.ToolTipText = "Uloz XML";
            this.tsSaveXML.Click += new System.EventHandler(this.tsSaveXML_Click);
            // 
            // toolStripButton1
            // 
            this.toolStripButton1.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton1.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton1.Image")));
            this.toolStripButton1.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton1.Name = "toolStripButton1";
            this.toolStripButton1.Size = new System.Drawing.Size(23, 22);
            this.toolStripButton1.Text = "toolStripButton1";
            this.toolStripButton1.ToolTipText = "Uloz do databaze";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            // 
            // lstToolbox
            // 
            this.lstToolbox.BackColor = System.Drawing.SystemColors.Control;
            this.lstToolbox.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.lstToolbox.Dock = System.Windows.Forms.DockStyle.Top;
            this.lstToolbox.Font = new System.Drawing.Font("Tahoma", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lstToolbox.IntegralHeight = false;
            this.lstToolbox.ItemHeight = 16;
            this.lstToolbox.Location = new System.Drawing.Point(0, 25);
            this.lstToolbox.Name = "lstToolbox";
            this.lstToolbox.SelectedCategory = null;
            this.lstToolbox.Size = new System.Drawing.Size(237, 241);
            this.lstToolbox.Sorted = true;
            this.lstToolbox.TabIndex = 2;
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
            // 
            // tsLoadXml
            // 
            this.tsLoadXml.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsLoadXml.Image = ((System.Drawing.Image)(resources.GetObject("tsLoadXml.Image")));
            this.tsLoadXml.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsLoadXml.Name = "tsLoadXml";
            this.tsLoadXml.Size = new System.Drawing.Size(23, 22);
            this.tsLoadXml.Text = "toolStripButton2";
            this.tsLoadXml.ToolTipText = "Naèíst soubor";
            this.tsLoadXml.Click += new System.EventHandler(this.tsLoadXml_Click);
            // 
            // cDesignerMainControl
            // 
            this.Controls.Add(this.pnlViewHost);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.splitter1);
            this.Controls.Add(this.pLeft);
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Name = "cDesignerMainControl";
            this.Size = new System.Drawing.Size(824, 539);
            this.pLeft.ResumeLayout(false);
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.ResumeLayout(false);

		}
		#endregion

		

		private ServiceContainer serviceContainer = null;
		private MenuCommandService menuService = null;
        IRootDesigner rootDesigner;
        Form DesignerForm;
            
        public void Initialize()
        {
			
			
			// Initialise service container and designer host
			serviceContainer = new ServiceContainer();
			serviceContainer.AddService(typeof(INameCreationService), new NameCreationService());
			serviceContainer.AddService(typeof(IUIService), new UIService(this));
			

			// Add toolbox service
			serviceContainer.AddService(typeof(IToolboxService), lstToolbox);
			lstToolbox.designPanel = pnlViewHost;
			PopulateToolbox(lstToolbox);

			// Add menu command service
			menuService = new MenuCommandService();
			serviceContainer.AddService(typeof(IMenuCommandService), menuService);
            host = new DesignerHost(serviceContainer);       
            // Start the designer host off with a Form to design
            Control view;
            DesignerForm = (Form)host.CreateComponent(typeof(Form));
			DesignerForm.TopLevel = false;
            DesignerForm.Text = "Designer";

            if (rootDesigner == null)
            {
                // Get the root designer for the form and add its design view to this form
                rootDesigner = (IRootDesigner)host.GetDesigner(DesignerForm);
                view = (Control)rootDesigner.GetView(ViewTechnology.WindowsForms);
                view.Dock = DockStyle.Fill;
                pnlViewHost.Controls.Add(view);

                // Subscribe to the selectionchanged event and activate the designer
                ISelectionService s = (ISelectionService)serviceContainer.GetService(typeof(ISelectionService));
                s.SelectionChanged += new EventHandler(OnSelectionChanged);
                host.Activate();
            }
            else
            {
                
            }
		}

		private void PopulateToolbox(IToolboxService toolbox)
		{
            toolbox.AddToolboxItem(new ToolboxItem(typeof(cMotionDetector)));
            toolbox.AddToolboxItem(new ToolboxItem(typeof(cSmokeDetector)));
            toolbox.AddToolboxItem(new ToolboxItem(typeof(cSiren)));
            toolbox.AddToolboxItem(new ToolboxItem(typeof(cRoom)));
		}

		private void OnSelectionChanged(object sender, System.EventArgs e)
		{
			ISelectionService s = (ISelectionService)serviceContainer.GetService(typeof(ISelectionService));

			object[] selection;
			if (s.SelectionCount == 0)
				propertyGrid.SelectedObject = null;
			else
			{
				selection = new object[s.SelectionCount];
				s.GetSelectedComponents().CopyTo(selection, 0);
				propertyGrid.SelectedObjects = selection;
			}

			if (s.PrimarySelection == null)
				lblSelectedComponent.Text = "";
			else
			{
				IComponent component = (IComponent)s.PrimarySelection;
				lblSelectedComponent.Text = component.Site.Name + " (" + component.GetType().Name + ")";
			}
		}

		private void mnuDelete_Click(object sender, System.EventArgs e)
		{
			menuService.GlobalInvoke(StandardCommands.Delete);
		}

        private void tsAddLayer_Click(object sender, EventArgs e)
        {
            TreeNode l_newNode = new TreeNode("NewLayer");
            tvDevices.Nodes.Add(l_newNode);
            l_newNode.BeginEdit();
           
        }

        private void tvDevices_AfterLabelEdit(object sender, NodeLabelEditEventArgs e)
        {
            
        }

        private void tsAddSubNode_Click(object sender, EventArgs e)
        {
            TreeNode l_parent = tvDevices.SelectedNode;
            TreeNode l_newNode = new TreeNode("NewLayer");
            if (l_parent != null)
            {
                l_parent.Nodes.Add(l_newNode);
                l_newNode.BeginEdit();
            }
            
            
        }

        private void tvDevices_AfterSelect(object sender, TreeViewEventArgs e)
        {
            if (e.Node.Parent == null)
            {
                // we have root node - refresh designer
                DesignerForm.Text = "Designer " + e.Node.Text;
                DesignerForm.Controls.Clear();
                if(e.Node.Tag!=null)
                    foreach (Control l_ctrl in (e.Node.Tag as List<Control>))
                        DesignerForm.Controls.Add(l_ctrl) ;
            }
            else
            {
                // we have child item - set focus to it
            }
        }

        private void tvDevices_BeforeSelect(object sender, TreeViewCancelEventArgs e)
        {
            List<Control> l_col = new List<Control>();
            foreach (Control l_ctrl in DesignerForm.Controls)
            {
                l_col.Add(l_ctrl);
            }
            if(tvDevices.SelectedNode!=null)
                tvDevices.SelectedNode.Tag = l_col;
        }

        private void tsSaveXML_Click(object sender, EventArgs e)
        {
            SaveFileDialog l_saveDlg = new SaveFileDialog();
            l_saveDlg.DefaultExt = "xml";
            if (l_saveDlg.ShowDialog() == DialogResult.OK)
            {
                string l_xml = "<HeniHouse>" + "\n";
                foreach (TreeNode l_node in tvDevices.Nodes)
                {
                    l_xml += "<Layer name=\"" + l_node.Text + "\">" + "\n";

                    foreach (Control l_ctrl in (l_node.Tag as List<Control>))
                    {
                        l_xml += "<Room name=\"" + l_ctrl.Name + "\" left=\"" + l_ctrl.Left + "\" top=\"" + l_ctrl.Top + "\" width=\"" + l_ctrl.Width + "\" height=\"" + l_ctrl.Height + "\">" + "\n";
                        l_xml += "<Devices>" + "\n";
                        foreach (Control l_ctrlChild in l_ctrl.Controls)
                        {
                            l_xml += "<Device name=\"" + l_ctrlChild.Name + "\" left=\"" + l_ctrlChild.Left + "\" top=\"" + l_ctrlChild.Top + "\" width=\"" + l_ctrlChild.Width + "\" height=\"" + l_ctrlChild.Height + "\"/>" + "\n";                       
                        }
                        l_xml += "</Devices>" + "\n";
                        l_xml += "</Room>";
                    }
                    l_xml += "</Layer>" + "\n";
                }

                l_xml += "</HeniHouse>";

                StreamWriter l_sw = new StreamWriter(l_saveDlg.FileName);
                l_sw.Write(l_xml);
                l_sw.Close();

            }
        }

        private void tsLoadXml_Click(object sender, EventArgs e)
        {
            OpenFileDialog l_openDlg = new OpenFileDialog();
            l_openDlg.Filter = "*.xml";
            if (l_openDlg.ShowDialog() == DialogResult.OK)
            {

                StreamReader l_sr = new StreamReader(l_openDlg.FileName);
                string l_xml = l_sr.ReadToEnd();
                l_sr.Close();


                //string l_xml = "<HeniHouse>" + "\n";
                //foreach (TreeNode l_node in tvDevices.Nodes)
                //{
                //    l_xml += "<Layer name=\"" + l_node.Text + "\">" + "\n";

                //    foreach (Control l_ctrl in (l_node.Tag as List<Control>))
                //    {
                //        l_xml += "<Room name=\"" + l_ctrl.Name + "\" left=\"" + l_ctrl.Left + "\" top=\"" + l_ctrl.Top + "\" width=\"" + l_ctrl.Width + "\" height=\"" + l_ctrl.Height + "\">" + "\n";
                //        l_xml += "<Devices>" + "\n";
                //        foreach (Control l_ctrlChild in l_ctrl.Controls)
                //        {
                //            l_xml += "<Device name=\"" + l_ctrlChild.Name + "\" left=\"" + l_ctrlChild.Left + "\" top=\"" + l_ctrlChild.Top + "\" width=\"" + l_ctrlChild.Width + "\" height=\"" + l_ctrlChild.Height + "\"/>" + "\n";
                //        }
                //        l_xml += "</Devices>" + "\n";
                //        l_xml += "</Room>";
                //    }
                //    l_xml += "</Layer>" + "\n";
                //}

                //l_xml += "</HeniHouse>";

                

            }
        }

	}
}
