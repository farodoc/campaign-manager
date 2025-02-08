import axios from "axios";
import { Campaign } from "./types";

const API_URL = "http://localhost:8080/v1/campaigns";

export const getCampaigns = () => axios.get<Campaign[]>(`${API_URL}`);

export const getCampaign = (id: number) =>
  axios.get<Campaign>(`${API_URL}/${id}`);

export const createCampaign = (campaign: Campaign) =>
  axios.post(`${API_URL}`, campaign);

export const updateCampaign = (id: number, campaign: Campaign) =>
  axios.put(`${API_URL}/${id}`, campaign);

export const deleteCampaign = (id: number) => axios.delete(`${API_URL}/${id}`);
