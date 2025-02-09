import axios from "axios";
import { Campaign } from "./types";

const API_URL = "http://localhost:8080/v1";
const USER_ID = 1;

export const getCampaigns = () => axios.get<Campaign[]>(`${API_URL}/campaigns`);

export const getCampaign = (id: number) =>
  axios.get<Campaign>(`${API_URL}/campaigns/${id}`);

export const createCampaign = (campaign: Campaign) =>
  axios.post(`${API_URL}/campaigns`, campaign);

export const updateCampaign = (id: number, campaign: Campaign) =>
  axios.put(`${API_URL}/campaigns/${id}`, campaign);

export const deleteCampaign = (id: number) =>
  axios.delete(`${API_URL}/campaigns/${id}`);

export const getUserBalance = async () => {
  const response = await axios.get(`${API_URL}/users/${USER_ID}/balance`);
  return response.data;
};

export const getKeywordSuggestions = async () => {
  const response = await axios.get(`${API_URL}/suggestions/keywords`);
  return response.data;
};

export const getTowns = async () => {
  const response = await axios.get(`${API_URL}/suggestions/towns`);
  return response.data;
};
