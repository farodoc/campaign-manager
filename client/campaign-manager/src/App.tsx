import { useEffect, useState } from "react";
import {
  getCampaigns,
  createCampaign,
  updateCampaign,
  deleteCampaign,
  getUserBalance,
  getKeywordSuggestions,
  getTowns,
} from "./api";
import { Campaign } from "./types";
import {
  Container,
  TextField,
  Checkbox,
  Button,
  Typography,
  Card,
  CardContent,
  CardActions,
  Box,
  Autocomplete,
  MenuItem,
  Alert,
} from "@mui/material";
import "./App.css";

function App() {
  const [campaigns, setCampaigns] = useState<Campaign[]>([]);
  const [towns, setTowns] = useState<string[]>([]);
  const [keywordSuggestions, setKeywordSuggestions] = useState<string[]>([]);
  const [userBalance, setUserBalance] = useState<number>(0);
  const [errorMessages, setErrorMessages] = useState<{ [key: string]: string }>(
    {}
  );

  const [newCampaign, setNewCampaign] = useState<Campaign>({
    name: "Test Name",
    keywords: ["blockchain"],
    bidAmount: 1,
    campaignFund: 1,
    status: true,
    town: "Krakow",
    radius: 1,
  });

  useEffect(() => {
    fetchCampaigns();
    fetchUserBalance();
    fetchTowns();
    fetchKeywordSuggestions();
  }, []);

  const fetchCampaigns = async () => {
    const response = await getCampaigns();
    setCampaigns(response.data);
  };

  const fetchUserBalance = async () => {
    const balance = await getUserBalance();
    setUserBalance(balance);
  };

  const fetchTowns = async () => {
    const townsList = await getTowns();
    setTowns(townsList);
  };

  const fetchKeywordSuggestions = async () => {
    const suggestions = await getKeywordSuggestions();
    setKeywordSuggestions(suggestions);
  };

  const handleCreateCampaign = async () => {
    if (newCampaign.campaignFund > userBalance) {
      alert("Insufficient funds!");
      return;
    }

    try {
      await createCampaign(newCampaign);
      fetchUserBalance();
      fetchCampaigns();
      setErrorMessages({});
    } catch (error: any) {
      if (error.response && error.response.status === 400) {
        setErrorMessages(error.response.data);
      } else {
        alert("An unexpected error occurred.");
      }
    }
  };

  const handleKeywordChange = async (
    event: React.ChangeEvent<{}>,
    values: string[]
  ) => {
    setNewCampaign({ ...newCampaign, keywords: values });
  };

  const handleUpdateCampaign = async (id: number, campaign: Campaign) => {
    await updateCampaign(id, campaign);
    fetchUserBalance();
    fetchCampaigns();
  };

  const handleDeleteCampaign = async (id: number) => {
    await deleteCampaign(id);
    fetchUserBalance();
    fetchCampaigns();
  };

  return (
    <Container className="container">
      <Typography variant="h1" color="primary" gutterBottom>
        Campaign Manager
      </Typography>
      <Typography variant="h5" color="secondary">
        Account Balance: ${userBalance.toFixed(2)}
      </Typography>
      <Box mb={4}>
        <Typography variant="h2" color="secondary" gutterBottom>
          Create Campaign
        </Typography>
        {Object.keys(errorMessages).length > 0 && (
          <Box mb={2}>
            {Object.entries(errorMessages).map(([key, message]) => (
              <Alert severity="error" key={key}>
                {message}
              </Alert>
            ))}
          </Box>
        )}
        <TextField
          label="Name"
          value={newCampaign.name}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, name: e.target.value })
          }
          margin="normal"
          error={!!errorMessages.name}
          helperText={errorMessages.name}
        />
        <Autocomplete
          multiple
          freeSolo
          options={keywordSuggestions}
          value={newCampaign.keywords}
          onChange={handleKeywordChange}
          renderInput={(params) => (
            <TextField
              {...params}
              label="Keywords"
              margin="normal"
              error={!!errorMessages.keywords}
              helperText={errorMessages.keywords}
            />
          )}
        />
        <TextField
          label="Bid Amount"
          type="number"
          value={newCampaign.bidAmount}
          onChange={(e) =>
            setNewCampaign({
              ...newCampaign,
              bidAmount: Math.max(1, parseFloat(e.target.value)),
            })
          }
          margin="normal"
        />
        <TextField
          label="Campaign Fund"
          type="number"
          value={newCampaign.campaignFund}
          onChange={(e) =>
            setNewCampaign({
              ...newCampaign,
              campaignFund: parseFloat(e.target.value),
            })
          }
          margin="normal"
        />
        <Checkbox
          checked={newCampaign.status}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, status: e.target.checked })
          }
        />
        <TextField
          select
          label="Town"
          value={newCampaign.town}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, town: e.target.value })
          }
          margin="normal"
        >
          {towns.map((town) => (
            <MenuItem key={town} value={town}>
              {town}
            </MenuItem>
          ))}
        </TextField>
        <TextField
          label="Radius (km)"
          type="number"
          value={newCampaign.radius}
          onChange={(e) =>
            setNewCampaign({ ...newCampaign, radius: parseInt(e.target.value) })
          }
          margin="normal"
        />
        <Button
          variant="contained"
          color="primary"
          onClick={handleCreateCampaign}
        >
          Create
        </Button>
      </Box>
      <Box>
        <Typography variant="h2" color="secondary" gutterBottom>
          Campaigns
        </Typography>
        {campaigns.map((campaign) => (
          <Card key={campaign.id} className="custom-card">
            <CardContent>
              <Typography variant="h3" color="primary">
                {campaign.name}
              </Typography>
              <Typography color="textSecondary">
                Keywords: {campaign.keywords.join(", ")}
              </Typography>
              <Typography color="textSecondary">
                Bid Amount: {campaign.bidAmount}
              </Typography>
              <Typography color="textSecondary">
                Campaign Fund: {campaign.campaignFund}
              </Typography>
              <Typography color="textSecondary">
                Status: {campaign.status ? "Active" : "Inactive"}
              </Typography>
              <Typography color="textSecondary">
                Town: {campaign.town}
              </Typography>
              <Typography color="textSecondary">
                Radius: {campaign.radius}
              </Typography>
            </CardContent>
            <CardActions>
              <Button
                variant="contained"
                color="primary"
                onClick={() => handleUpdateCampaign(campaign.id!, newCampaign)}
              >
                Update
              </Button>
              <Button
                variant="contained"
                color="secondary"
                onClick={() => handleDeleteCampaign(campaign.id!)}
              >
                Delete
              </Button>
            </CardActions>
          </Card>
        ))}
      </Box>
    </Container>
  );
}

export default App;
