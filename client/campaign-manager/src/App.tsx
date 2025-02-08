import { useEffect, useState } from "react";
import {
  getCampaigns,
  createCampaign,
  updateCampaign,
  deleteCampaign,
} from "./api";
import { Campaign } from "./types";
import "./App.css";

function App() {
  const [campaigns, setCampaigns] = useState<Campaign[]>([]);
  const [newCampaign, setNewCampaign] = useState<Campaign>({
    name: "",
    keywords: [],
    bidAmount: 0,
    campaignFund: 0,
    status: false,
    town: "",
    radius: 0,
  });

  useEffect(() => {
    fetchCampaigns();
  }, []);

  const fetchCampaigns = async () => {
    const response = await getCampaigns();
    setCampaigns(response.data);
  };

  const handleCreateCampaign = async () => {
    await createCampaign(newCampaign);
    fetchCampaigns();
  };

  const handleUpdateCampaign = async (id: number) => {
    await updateCampaign(id, newCampaign);
    fetchCampaigns();
  };

  const handleDeleteCampaign = async (id: number) => {
    await deleteCampaign(id);
    fetchCampaigns();
  };

  return (
    <div className="App">
      <h1>Campaign Manager</h1>
      <div>
        <h2>Create Campaign</h2>
        <input
          type="text"
          placeholder="Name"
          value={newCampaign.name}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, name: e.target.value })
          }
        />
        <input
          type="text"
          placeholder="Keywords"
          value={newCampaign.keywords.join(", ")}
          onChange={(e) =>
            setNewCampaign({
              ...newCampaign,
              keywords: e.target.value.split(", "),
            })
          }
        />
        <input
          type="number"
          placeholder="Bid Amount"
          value={newCampaign.bidAmount}
          onChange={(e) =>
            setNewCampaign({
              ...newCampaign,
              bidAmount: parseFloat(e.target.value),
            })
          }
        />
        <input
          type="number"
          placeholder="Campaign Fund"
          value={newCampaign.campaignFund}
          onChange={(e) =>
            setNewCampaign({
              ...newCampaign,
              campaignFund: parseFloat(e.target.value),
            })
          }
        />
        <input
          type="checkbox"
          checked={newCampaign.status}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, status: e.target.checked })
          }
        />
        <input
          type="text"
          placeholder="Town"
          value={newCampaign.town}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, town: e.target.value })
          }
        />
        <input
          type="number"
          placeholder="Radius"
          value={newCampaign.radius}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, radius: parseInt(e.target.value) })
          }
        />
        <button onClick={handleCreateCampaign}>Create</button>
      </div>
      <div>
        <h2>Campaigns</h2>
        {campaigns.map((campaign) => (
          <div key={campaign.id}>
            <h3>{campaign.name}</h3>
            <p>Keywords: {campaign.keywords.join(", ")}</p>
            <p>Bid Amount: {campaign.bidAmount}</p>
            <p>Campaign Fund: {campaign.campaignFund}</p>
            <p>Status: {campaign.status ? "Active" : "Inactive"}</p>
            <p>Town: {campaign.town}</p>
            <p>Radius: {campaign.radius}</p>
            <button onClick={() => handleUpdateCampaign(campaign.id!)}>
              Update
            </button>
            <button onClick={() => handleDeleteCampaign(campaign.id!)}>
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
